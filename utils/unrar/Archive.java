package com.movieboxpro.android.utils.unrar;

import com.movieboxpro.android.utils.unrar.exception.RarException;
import com.movieboxpro.android.utils.unrar.io.IReadOnlyAccess;
import com.movieboxpro.android.utils.unrar.io.ReadOnlyAccessFile;
import com.movieboxpro.android.utils.unrar.rarfile.AVHeader;
import com.movieboxpro.android.utils.unrar.rarfile.BaseBlock;
import com.movieboxpro.android.utils.unrar.rarfile.BlockHeader;
import com.movieboxpro.android.utils.unrar.rarfile.CommentHeader;
import com.movieboxpro.android.utils.unrar.rarfile.EAHeader;
import com.movieboxpro.android.utils.unrar.rarfile.EndArcHeader;
import com.movieboxpro.android.utils.unrar.rarfile.FileHeader;
import com.movieboxpro.android.utils.unrar.rarfile.MacInfoHeader;
import com.movieboxpro.android.utils.unrar.rarfile.MainHeader;
import com.movieboxpro.android.utils.unrar.rarfile.MarkHeader;
import com.movieboxpro.android.utils.unrar.rarfile.ProtectHeader;
import com.movieboxpro.android.utils.unrar.rarfile.SignHeader;
import com.movieboxpro.android.utils.unrar.rarfile.SubBlockHeader;
import com.movieboxpro.android.utils.unrar.rarfile.SubBlockHeaderType;
import com.movieboxpro.android.utils.unrar.rarfile.UnixOwnersHeader;
import com.movieboxpro.android.utils.unrar.rarfile.UnrarHeadertype;
import com.movieboxpro.android.utils.unrar.unpack.ComprDataIO;
import com.movieboxpro.android.utils.unrar.unpack.Unpack;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes3.dex */
public class Archive implements Closeable {
    private static Logger logger = Logger.getLogger(Archive.class.getName());
    private long arcDataCRC;
    private int currentHeaderIndex;
    private final ComprDataIO dataIO;
    private boolean encrypted;
    private EndArcHeader endHeader;
    private File file;
    private final List<BaseBlock> headers;
    private MarkHeader markHead;
    private MainHeader newMhd;
    private IReadOnlyAccess rof;
    private int sfxSize;
    private long totalPackedRead;
    private long totalPackedSize;
    private Unpack unpack;
    private final UnrarCallback unrarCallback;

    public Archive(File file) throws RarException, IOException {
        this(file, null);
    }

    public Archive(File file, UnrarCallback unrarCallback) throws RarException, IOException {
        this.headers = new ArrayList();
        this.markHead = null;
        this.newMhd = null;
        this.endHeader = null;
        this.arcDataCRC = -1L;
        this.encrypted = false;
        this.sfxSize = 0;
        this.totalPackedSize = 0L;
        this.totalPackedRead = 0L;
        setFile(file);
        this.unrarCallback = unrarCallback;
        this.dataIO = new ComprDataIO(this);
    }

    public File getFile() {
        return this.file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFile(File file) throws IOException {
        this.file = file;
        this.totalPackedSize = 0L;
        this.totalPackedRead = 0L;
        close();
        this.rof = new ReadOnlyAccessFile(file);
        try {
            readHeaders();
        } catch (Exception e) {
            logger.log(Level.WARNING, "exception in archive constructor maybe file is encrypted or currupt", (Throwable) e);
        }
        for (BaseBlock baseBlock : this.headers) {
            if (baseBlock.getHeaderType() == UnrarHeadertype.FileHeader) {
                this.totalPackedSize += ((FileHeader) baseBlock).getFullPackSize();
            }
        }
        UnrarCallback unrarCallback = this.unrarCallback;
        if (unrarCallback != null) {
            unrarCallback.volumeProgressChanged(this.totalPackedRead, this.totalPackedSize);
        }
    }

    public void bytesReadRead(int i) {
        if (i > 0) {
            long j = this.totalPackedRead + i;
            this.totalPackedRead = j;
            UnrarCallback unrarCallback = this.unrarCallback;
            if (unrarCallback != null) {
                unrarCallback.volumeProgressChanged(j, this.totalPackedSize);
            }
        }
    }

    public IReadOnlyAccess getRof() {
        return this.rof;
    }

    public List<FileHeader> getFileHeaders() {
        ArrayList arrayList = new ArrayList();
        for (BaseBlock baseBlock : this.headers) {
            if (baseBlock.getHeaderType().equals(UnrarHeadertype.FileHeader)) {
                arrayList.add((FileHeader) baseBlock);
            }
        }
        return arrayList;
    }

    public FileHeader nextFileHeader() {
        BaseBlock baseBlock;
        int size = this.headers.size();
        do {
            int i = this.currentHeaderIndex;
            if (i >= size) {
                return null;
            }
            List<BaseBlock> list = this.headers;
            this.currentHeaderIndex = i + 1;
            baseBlock = list.get(i);
        } while (baseBlock.getHeaderType() != UnrarHeadertype.FileHeader);
        return (FileHeader) baseBlock;
    }

    public UnrarCallback getUnrarCallback() {
        return this.unrarCallback;
    }

    public boolean isEncrypted() {
        MainHeader mainHeader = this.newMhd;
        if (mainHeader != null) {
            return mainHeader.isEncrypted();
        }
        throw new NullPointerException("mainheader is null");
    }

    private void readHeaders() throws IOException, RarException {
        EndArcHeader endArcHeader;
        this.markHead = null;
        this.newMhd = null;
        this.endHeader = null;
        this.headers.clear();
        this.currentHeaderIndex = 0;
        long length = this.file.length();
        while (true) {
            byte[] bArr = new byte[7];
            long position = this.rof.getPosition();
            if (position >= length) {
                return;
            }
            System.out.print("\n--------reading header--------");
            if (this.rof.readFully(bArr, 7) == 0) {
                return;
            }
            BaseBlock baseBlock = new BaseBlock(bArr);
            baseBlock.setPositionInFile(position);
            switch (baseBlock.getHeaderType()) {
                case MarkHeader:
                    MarkHeader markHeader = new MarkHeader(baseBlock);
                    this.markHead = markHeader;
                    if (!markHeader.isSignature()) {
                        throw new RarException(RarException.RarExceptionType.badRarArchive);
                    }
                    this.headers.add(this.markHead);
                    break;
                case MainHeader:
                    int i = baseBlock.hasEncryptVersion() ? 7 : 6;
                    byte[] bArr2 = new byte[i];
                    this.rof.readFully(bArr2, i);
                    MainHeader mainHeader = new MainHeader(baseBlock, bArr2);
                    this.headers.add(mainHeader);
                    this.newMhd = mainHeader;
                    if (!mainHeader.isEncrypted()) {
                        break;
                    } else {
                        throw new RarException(RarException.RarExceptionType.rarEncryptedException);
                    }
                case SignHeader:
                    byte[] bArr3 = new byte[8];
                    this.rof.readFully(bArr3, 8);
                    this.headers.add(new SignHeader(baseBlock, bArr3));
                    System.out.print("HeaderType: SignHeader");
                    break;
                case AvHeader:
                    byte[] bArr4 = new byte[7];
                    this.rof.readFully(bArr4, 7);
                    this.headers.add(new AVHeader(baseBlock, bArr4));
                    System.out.print("headertype: AVHeader");
                    break;
                case CommHeader:
                    byte[] bArr5 = new byte[6];
                    this.rof.readFully(bArr5, 6);
                    CommentHeader commentHeader = new CommentHeader(baseBlock, bArr5);
                    this.headers.add(commentHeader);
                    System.out.print("method: " + ((int) commentHeader.getUnpMethod()) + "; 0x" + Integer.toHexString(commentHeader.getUnpMethod()));
                    this.rof.setPosition(commentHeader.getPositionInFile() + ((long) commentHeader.getHeaderSize()));
                    break;
                case EndArcHeader:
                    int i2 = baseBlock.hasArchiveDataCRC() ? 4 : 0;
                    if (baseBlock.hasVolumeNumber()) {
                        i2 += 2;
                    }
                    if (i2 > 0) {
                        byte[] bArr6 = new byte[i2];
                        this.rof.readFully(bArr6, i2);
                        endArcHeader = new EndArcHeader(baseBlock, bArr6);
                        System.out.print("HeaderType: endarch\ndatacrc:" + endArcHeader.getArchiveDataCRC());
                    } else {
                        System.out.print("HeaderType: endarch - no Data");
                        endArcHeader = new EndArcHeader(baseBlock, null);
                    }
                    this.headers.add(endArcHeader);
                    this.endHeader = endArcHeader;
                    System.out.print("\n--------end header--------");
                    return;
                default:
                    byte[] bArr7 = new byte[4];
                    this.rof.readFully(bArr7, 4);
                    BlockHeader blockHeader = new BlockHeader(baseBlock, bArr7);
                    int i3 = AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[blockHeader.getHeaderType().ordinal()];
                    if (i3 != 1 && i3 != 2) {
                        if (i3 == 3) {
                            int headerSize = (blockHeader.getHeaderSize() - 7) - 4;
                            byte[] bArr8 = new byte[headerSize];
                            this.rof.readFully(bArr8, headerSize);
                            ProtectHeader protectHeader = new ProtectHeader(blockHeader, bArr8);
                            System.out.print("totalblocks" + protectHeader.getTotalBlocks());
                            this.rof.setPosition(protectHeader.getPositionInFile() + ((long) protectHeader.getHeaderSize()));
                            break;
                        } else if (i3 == 4) {
                            byte[] bArr9 = new byte[3];
                            this.rof.readFully(bArr9, 3);
                            SubBlockHeader subBlockHeader = new SubBlockHeader(blockHeader, bArr9);
                            subBlockHeader.print();
                            int i4 = AnonymousClass1.$SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[subBlockHeader.getSubType().ordinal()];
                            if (i4 == 1) {
                                byte[] bArr10 = new byte[8];
                                this.rof.readFully(bArr10, 8);
                                MacInfoHeader macInfoHeader = new MacInfoHeader(subBlockHeader, bArr10);
                                macInfoHeader.print();
                                this.headers.add(macInfoHeader);
                                break;
                            } else if (i4 == 3) {
                                byte[] bArr11 = new byte[10];
                                this.rof.readFully(bArr11, 10);
                                EAHeader eAHeader = new EAHeader(subBlockHeader, bArr11);
                                eAHeader.print();
                                this.headers.add(eAHeader);
                                break;
                            } else if (i4 == 6) {
                                int headerSize2 = ((subBlockHeader.getHeaderSize() - 7) - 4) - 3;
                                byte[] bArr12 = new byte[headerSize2];
                                this.rof.readFully(bArr12, headerSize2);
                                UnixOwnersHeader unixOwnersHeader = new UnixOwnersHeader(subBlockHeader, bArr12);
                                unixOwnersHeader.print();
                                this.headers.add(unixOwnersHeader);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            logger.warning("Unknown Header");
                            throw new RarException(RarException.RarExceptionType.notRarArchive);
                        }
                    } else {
                        int headerSize3 = (blockHeader.getHeaderSize() - 7) - 4;
                        byte[] bArr13 = new byte[headerSize3];
                        this.rof.readFully(bArr13, headerSize3);
                        FileHeader fileHeader = new FileHeader(blockHeader, bArr13);
                        this.headers.add(fileHeader);
                        this.rof.setPosition(fileHeader.getPositionInFile() + fileHeader.getHeaderSize() + fileHeader.getFullPackSize());
                        break;
                    }
                    break;
            }
            System.out.print("\n--------end header--------");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.utils.unrar.Archive$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType;

        static {
            int[] iArr = new int[UnrarHeadertype.values().length];
            $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype = iArr;
            try {
                iArr[UnrarHeadertype.NewSubHeader.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.FileHeader.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.ProtectHeader.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.SubHeader.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.MarkHeader.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.MainHeader.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.SignHeader.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.AvHeader.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.CommHeader.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$UnrarHeadertype[UnrarHeadertype.EndArcHeader.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[SubBlockHeaderType.values().length];
            $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType = iArr2;
            try {
                iArr2[SubBlockHeaderType.MAC_HEAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[SubBlockHeaderType.BEEA_HEAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[SubBlockHeaderType.EA_HEAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[SubBlockHeaderType.NTACL_HEAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[SubBlockHeaderType.STREAM_HEAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$movieboxpro$android$utils$unrar$rarfile$SubBlockHeaderType[SubBlockHeaderType.UO_HEAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public void extractFile(FileHeader fileHeader, OutputStream outputStream) throws RarException {
        if (!this.headers.contains(fileHeader)) {
            throw new RarException(RarException.RarExceptionType.headerNotInArchive);
        }
        try {
            doExtractFile(fileHeader, outputStream);
        } catch (Exception e) {
            if (e instanceof RarException) {
                throw ((RarException) e);
            }
            throw new RarException(e);
        }
    }

    private void doExtractFile(FileHeader fileHeader, OutputStream outputStream) throws RarException, IOException {
        this.dataIO.init(outputStream);
        this.dataIO.init(fileHeader);
        this.dataIO.setUnpFileCRC(isOldFormat() ? 0L : -1L);
        if (this.unpack == null) {
            this.unpack = new Unpack(this.dataIO);
        }
        if (!fileHeader.isSolid()) {
            this.unpack.init(null);
        }
        this.unpack.setDestSize(fileHeader.getFullUnpackSize());
        try {
            this.unpack.doUnpack(fileHeader.getUnpVersion(), fileHeader.isSolid());
            FileHeader subHeader = this.dataIO.getSubHeader();
            if (((-1) ^ (subHeader.isSplitAfter() ? this.dataIO.getPackedCRC() : this.dataIO.getUnpFileCRC())) == subHeader.getFileCRC()) {
                return;
            }
            throw new RarException(RarException.RarExceptionType.crcError);
        } catch (Exception e) {
            this.unpack.cleanUp();
            if (e instanceof RarException) {
                throw ((RarException) e);
            }
            throw new RarException(e);
        }
    }

    public MainHeader getMainHeader() {
        return this.newMhd;
    }

    public boolean isOldFormat() {
        return this.markHead.isOldFormat();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IReadOnlyAccess iReadOnlyAccess = this.rof;
        if (iReadOnlyAccess != null) {
            iReadOnlyAccess.close();
            this.rof = null;
        }
        Unpack unpack = this.unpack;
        if (unpack != null) {
            unpack.cleanUp();
        }
    }
}
