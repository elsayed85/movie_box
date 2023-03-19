package com.movieboxpro.android.view.fragment.home;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.event.OpenFilterEvent;
import com.movieboxpro.android.model.CountryResponse;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.MediaPlayer;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoviesFragment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.view.fragment.home.MoviesFragment$getCountry$7", f = "MoviesFragment.kt", i = {}, l = {MediaPlayer.Event.EndReached, MediaPlayer.Event.SeekableChanged}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
public final class MoviesFragment$getCountry$7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ OpenFilterEvent $event;
    final /* synthetic */ String $json;
    int label;
    final /* synthetic */ MoviesFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoviesFragment$getCountry$7(MoviesFragment moviesFragment, String str, OpenFilterEvent openFilterEvent, Continuation<? super MoviesFragment$getCountry$7> continuation) {
        super(2, continuation);
        this.this$0 = moviesFragment;
        this.$json = str;
        this.$event = openFilterEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MoviesFragment$getCountry$7(this.this$0, this.$json, this.$event, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoviesFragment$getCountry$7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception unused) {
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.this$0, this.$event, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.countryList = ((CountryResponse) JSONObject.parseObject(this.$json, CountryResponse.class)).getCountry_list();
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(this.this$0, this.$event, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MoviesFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.home.MoviesFragment$getCountry$7$1", f = "MoviesFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.movieboxpro.android.view.fragment.home.MoviesFragment$getCountry$7$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OpenFilterEvent $event;
        int label;
        final /* synthetic */ MoviesFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MoviesFragment moviesFragment, OpenFilterEvent openFilterEvent, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = moviesFragment;
            this.$event = openFilterEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$event, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MoviesFragment moviesFragment = this.this$0;
                OpenFilterEvent openFilterEvent = this.$event;
                arrayList = moviesFragment.countryList;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                moviesFragment.openFilter(openFilterEvent, arrayList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MoviesFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.movieboxpro.android.view.fragment.home.MoviesFragment$getCountry$7$2", f = "MoviesFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.movieboxpro.android.view.fragment.home.MoviesFragment$getCountry$7$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ OpenFilterEvent $event;
        int label;
        final /* synthetic */ MoviesFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(MoviesFragment moviesFragment, OpenFilterEvent openFilterEvent, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = moviesFragment;
            this.$event = openFilterEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$event, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ArrayList arrayList;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MoviesFragment moviesFragment = this.this$0;
                OpenFilterEvent openFilterEvent = this.$event;
                arrayList = moviesFragment.countryList;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                moviesFragment.openFilter(openFilterEvent, arrayList);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
