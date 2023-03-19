package com.movieboxpro.android.utils.unrar.exception;
/* loaded from: classes3.dex */
public class RarException extends Exception {
    private RarExceptionType type;

    /* loaded from: classes3.dex */
    public enum RarExceptionType {
        notImplementedYet,
        crcError,
        notRarArchive,
        badRarArchive,
        unkownError,
        headerNotInArchive,
        wrongHeaderType,
        ioError,
        rarEncryptedException
    }

    public RarException(Exception exc) {
        super(RarExceptionType.unkownError.name(), exc);
        this.type = RarExceptionType.unkownError;
    }

    public RarException(RarException rarException) {
        super(rarException.getMessage(), rarException);
        this.type = rarException.getType();
    }

    public RarException(RarExceptionType rarExceptionType) {
        super(rarExceptionType.name());
        this.type = rarExceptionType;
    }

    public RarExceptionType getType() {
        return this.type;
    }

    public void setType(RarExceptionType rarExceptionType) {
        this.type = rarExceptionType;
    }
}
