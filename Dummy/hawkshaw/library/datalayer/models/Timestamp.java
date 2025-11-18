package com.hawkshaw.library.datalayer.models;

import B3.f;
import kotlinx.serialization.KSerializer;

@f(with = TimestampSerializer.class)
public final class Timestamp {
    public static final Companion Companion = new Companion((j3.f) null);
    private final long milliseconds;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return TimestampSerializer.INSTANCE;
        }
    }

    public Timestamp() {
        this(0, 1, (j3.f) null);
    }

    public static /* synthetic */ void getMilliseconds$annotations() {
    }

    public final long getMilliseconds() {
        return this.milliseconds;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Timestamp(long j5, int i5, j3.f fVar) {
        this((i5 & 1) != 0 ? 0 : j5);
    }

    public Timestamp(long j5) {
        this.milliseconds = j5;
    }
}
