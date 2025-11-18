package com.hawkshaw.library.datalayer.network.twirp;

import B3.f;
import D3.b;
import E3.H;
import E3.J;
import E3.q0;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import p3.q;
import r3.j;
import w3.w;

public interface ApiResponse<T> {

    public static final class DefaultImpls {
        public static <T> String getErrorMessage(ApiResponse<T> apiResponse) {
            if (!(apiResponse instanceof Error)) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            Error error = (Error) apiResponse;
            String message = error.getMessage();
            sb.append("Message: " + message);
            if (!j.U(error.getCode())) {
                String code = error.getCode();
                sb.append(", Code: " + code);
            }
            Map<String, String> meta = error.getMeta();
            if (meta != null && !meta.isEmpty()) {
                Map<String, String> meta2 = error.getMeta();
                sb.append(", Meta: " + meta2);
            }
            String sb2 = sb.toString();
            K.m(sb2, "toString(...)");
            return sb2;
        }

        public static <T> String getState(ApiResponse<T> apiResponse) {
            return apiResponse instanceof Error ? "Error" : apiResponse instanceof InProgress ? "InProgress" : apiResponse instanceof Success ? "Success" : "Unknown";
        }

        public static <T> boolean isError(ApiResponse<T> apiResponse) {
            return apiResponse instanceof Error;
        }

        public static <T> boolean isSuccess(ApiResponse<T> apiResponse) {
            return apiResponse instanceof Success;
        }
    }

    public static final class InProgress<T> implements ApiResponse<T> {
        public String getErrorMessage() {
            return DefaultImpls.getErrorMessage(this);
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }
    }

    public static final class Success<T> implements ApiResponse<T> {
        private final T result;

        public Success(T t4) {
            this.result = t4;
        }

        public String getErrorMessage() {
            return DefaultImpls.getErrorMessage(this);
        }

        public final T getResult() {
            return this.result;
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }
    }

    String getErrorMessage();

    String getState();

    boolean isError();

    boolean isSuccess();

    @f
    public static final class Error<T> implements ApiResponse<T> {
        private static final SerialDescriptor $cachedDescriptor;
        /* access modifiers changed from: private */
        public static final KSerializer[] $childSerializers;
        public static final Companion Companion = new Companion((j3.f) null);
        private final String code;
        private final String message;
        private final Map<String, String> meta;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final <T0> KSerializer serializer(KSerializer kSerializer) {
                K.n(kSerializer, "typeSerial0");
                return new ApiResponse$Error$$serializer(kSerializer);
            }
        }

        static {
            u0 u0Var = u0.f536a;
            $childSerializers = new KSerializer[]{null, null, new J(u0Var, u0Var, 1)};
            PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error", (H) null, 3);
            pluginGeneratedSerialDescriptor.l(NotificationCompat.CATEGORY_MESSAGE, false);
            pluginGeneratedSerialDescriptor.l("code", false);
            pluginGeneratedSerialDescriptor.l("meta", false);
            $cachedDescriptor = pluginGeneratedSerialDescriptor;
        }

        public /* synthetic */ Error(int i5, String str, String str2, Map map, q0 q0Var) {
            if (7 == (i5 & 7)) {
                this.message = str;
                this.code = str2;
                this.meta = map;
                return;
            }
            w.x(i5, 7, $cachedDescriptor);
            throw null;
        }

        public static /* synthetic */ void getMessage$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(Error error, b bVar, SerialDescriptor serialDescriptor, KSerializer kSerializer) {
            KSerializer[] kSerializerArr = $childSerializers;
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, error.message);
            qVar.f0(serialDescriptor, 1, error.code);
            qVar.s(serialDescriptor, 2, kSerializerArr[2], error.meta);
        }

        public final String getCode() {
            return this.code;
        }

        public String getErrorMessage() {
            return DefaultImpls.getErrorMessage(this);
        }

        public final String getMessage() {
            return this.message;
        }

        public final Map<String, String> getMeta() {
            return this.meta;
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }

        public Error(String str, String str2, Map<String, String> map) {
            K.n(str, "message");
            K.n(str2, "code");
            this.message = str;
            this.code = str2;
            this.meta = map;
        }
    }
}
