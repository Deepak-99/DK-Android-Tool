package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class Location {
    public static final Companion Companion = new Companion((j3.f) null);
    private final float accuracy;
    private final double altitude;
    private final float bearing;
    private final boolean isMock;
    private final double latitude;
    private final double longitude;
    private final String provider;
    private final float speed;
    private final long time;
    private final float verticalAccuracyMeters;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Location$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Location(int i5, double d5, double d6, double d7, float f5, float f6, float f7, float f8, String str, long j5, boolean z4, q0 q0Var) {
        if (1023 == (i5 & 1023)) {
            this.altitude = d5;
            this.latitude = d6;
            this.longitude = d7;
            this.accuracy = f5;
            this.verticalAccuracyMeters = f6;
            this.speed = f7;
            this.bearing = f8;
            this.provider = str;
            this.time = j5;
            this.isMock = z4;
            return;
        }
        w.x(i5, 1023, Location$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ Location copy$default(Location location, double d5, double d6, double d7, float f5, float f6, float f7, float f8, String str, long j5, boolean z4, int i5, Object obj) {
        Location location2 = location;
        int i6 = i5;
        return location.copy((i6 & 1) != 0 ? location2.altitude : d5, (i6 & 2) != 0 ? location2.latitude : d6, (i6 & 4) != 0 ? location2.longitude : d7, (i6 & 8) != 0 ? location2.accuracy : f5, (i6 & 16) != 0 ? location2.verticalAccuracyMeters : f6, (i6 & 32) != 0 ? location2.speed : f7, (i6 & 64) != 0 ? location2.bearing : f8, (i6 & 128) != 0 ? location2.provider : str, (i6 & 256) != 0 ? location2.time : j5, (i6 & NotificationCompat.FLAG_GROUP_SUMMARY) != 0 ? location2.isMock : z4);
    }

    public static /* synthetic */ void getAccuracy$annotations() {
    }

    public static /* synthetic */ void getAltitude$annotations() {
    }

    public static /* synthetic */ void getBearing$annotations() {
    }

    public static /* synthetic */ void getLatitude$annotations() {
    }

    public static /* synthetic */ void getLongitude$annotations() {
    }

    public static /* synthetic */ void getProvider$annotations() {
    }

    public static /* synthetic */ void getSpeed$annotations() {
    }

    public static /* synthetic */ void getTime$annotations() {
    }

    public static /* synthetic */ void getVerticalAccuracyMeters$annotations() {
    }

    public static /* synthetic */ void isMock$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Location location, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.Y(serialDescriptor, 0, location.altitude);
        qVar.Y(serialDescriptor, 1, location.latitude);
        qVar.Y(serialDescriptor, 2, location.longitude);
        qVar.a0(serialDescriptor, 3, location.accuracy);
        qVar.a0(serialDescriptor, 4, location.verticalAccuracyMeters);
        qVar.a0(serialDescriptor, 5, location.speed);
        qVar.a0(serialDescriptor, 6, location.bearing);
        qVar.s(serialDescriptor, 7, u0.f536a, location.provider);
        qVar.d0(serialDescriptor, 8, location.time);
        qVar.X(serialDescriptor, 9, location.isMock);
    }

    public final double component1() {
        return this.altitude;
    }

    public final boolean component10() {
        return this.isMock;
    }

    public final double component2() {
        return this.latitude;
    }

    public final double component3() {
        return this.longitude;
    }

    public final float component4() {
        return this.accuracy;
    }

    public final float component5() {
        return this.verticalAccuracyMeters;
    }

    public final float component6() {
        return this.speed;
    }

    public final float component7() {
        return this.bearing;
    }

    public final String component8() {
        return this.provider;
    }

    public final long component9() {
        return this.time;
    }

    public final Location copy(double d5, double d6, double d7, float f5, float f6, float f7, float f8, String str, long j5, boolean z4) {
        return new Location(d5, d6, d7, f5, f6, f7, f8, str, j5, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location location = (Location) obj;
        return Double.compare(this.altitude, location.altitude) == 0 && Double.compare(this.latitude, location.latitude) == 0 && Double.compare(this.longitude, location.longitude) == 0 && Float.compare(this.accuracy, location.accuracy) == 0 && Float.compare(this.verticalAccuracyMeters, location.verticalAccuracyMeters) == 0 && Float.compare(this.speed, location.speed) == 0 && Float.compare(this.bearing, location.bearing) == 0 && K.f(this.provider, location.provider) && this.time == location.time && this.isMock == location.isMock;
    }

    public final float getAccuracy() {
        return this.accuracy;
    }

    public final double getAltitude() {
        return this.altitude;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final long getTime() {
        return this.time;
    }

    public final float getVerticalAccuracyMeters() {
        return this.verticalAccuracyMeters;
    }

    public int hashCode() {
        int hashCode = Double.hashCode(this.latitude);
        int hashCode2 = Double.hashCode(this.longitude);
        int hashCode3 = Float.hashCode(this.accuracy);
        int hashCode4 = Float.hashCode(this.verticalAccuracyMeters);
        int hashCode5 = (Float.hashCode(this.bearing) + ((Float.hashCode(this.speed) + ((hashCode4 + ((hashCode3 + ((hashCode2 + ((hashCode + (Double.hashCode(this.altitude) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        String str = this.provider;
        int hashCode6 = str == null ? 0 : str.hashCode();
        return Boolean.hashCode(this.isMock) + ((Long.hashCode(this.time) + ((hashCode5 + hashCode6) * 31)) * 31);
    }

    public final boolean isMock() {
        return this.isMock;
    }

    public String toString() {
        double d5 = this.altitude;
        double d6 = this.latitude;
        double d7 = this.longitude;
        float f5 = this.accuracy;
        float f6 = this.verticalAccuracyMeters;
        float f7 = this.speed;
        float f8 = this.bearing;
        String str = this.provider;
        long j5 = this.time;
        boolean z4 = this.isMock;
        return "Location(altitude=" + d5 + ", latitude=" + d6 + ", longitude=" + d7 + ", accuracy=" + f5 + ", verticalAccuracyMeters=" + f6 + ", speed=" + f7 + ", bearing=" + f8 + ", provider=" + str + ", time=" + j5 + ", isMock=" + z4 + ")";
    }

    public Location(double d5, double d6, double d7, float f5, float f6, float f7, float f8, String str, long j5, boolean z4) {
        this.altitude = d5;
        this.latitude = d6;
        this.longitude = d7;
        this.accuracy = f5;
        this.verticalAccuracyMeters = f6;
        this.speed = f7;
        this.bearing = f8;
        this.provider = str;
        this.time = j5;
        this.isMock = z4;
    }
}
