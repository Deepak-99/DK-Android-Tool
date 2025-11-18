package com.hawkshaw.library.datalayer.models;

import Y1.K;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import t3.F;

public final class TimestampSerializer implements KSerializer {
    public static final TimestampSerializer INSTANCE = new TimestampSerializer();
    private static final SerialDescriptor descriptor = F.g("Timestamp");
    private static final SimpleDateFormat rfc3339Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US);

    private TimestampSerializer() {
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public Timestamp deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        String A4 = decoder.A();
        SimpleDateFormat simpleDateFormat = rfc3339Format;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return new Timestamp(simpleDateFormat.parse(A4).getTime());
    }

    public void serialize(Encoder encoder, Timestamp timestamp) {
        K.n(encoder, "encoder");
        K.n(timestamp, "value");
        SimpleDateFormat simpleDateFormat = rfc3339Format;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(timestamp.getMilliseconds()));
        K.k(format);
        encoder.r(format);
    }
}
