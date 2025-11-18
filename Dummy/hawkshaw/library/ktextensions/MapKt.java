package com.hawkshaw.library.ktextensions;

import F3.b;
import F3.i;
import F3.o;
import G3.I;
import W2.h;
import X2.l;
import X2.r;
import X2.w;
import Y1.C0110h;
import Y1.K;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.a;
import kotlinx.serialization.json.c;
import kotlinx.serialization.json.d;
import r3.f;

public final class MapKt {
    public static final String encodeToString(b bVar, h... hVarArr) {
        LinkedHashMap linkedHashMap;
        K.n(bVar, "<this>");
        K.n(hVarArr, "pairs");
        int length = hVarArr.length;
        if (length == 0) {
            linkedHashMap = r.f2603D;
        } else if (length != 1) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(C0110h.B(hVarArr.length));
            w.a0(linkedHashMap2, hVarArr);
            linkedHashMap = linkedHashMap2;
        } else {
            linkedHashMap = C0110h.C(hVarArr[0]);
        }
        return bVar.b(kotlinx.serialization.json.b.Companion.serializer(), toJsonElement(linkedHashMap));
    }

    public static final Object parseValue(kotlinx.serialization.json.b bVar) {
        K.n(bVar, "<this>");
        if (bVar instanceof d) {
            return parseValue((d) bVar);
        }
        if (bVar instanceof a) {
            Iterable<kotlinx.serialization.json.b> iterable = (Iterable) bVar;
            ArrayList arrayList = new ArrayList(l.L0(iterable, 10));
            for (kotlinx.serialization.json.b parseValue : iterable) {
                arrayList.add(parseValue(parseValue));
            }
            return arrayList;
        } else if (bVar instanceof c) {
            throw new Error("File: Map.kt - JsonElement.parseValue JsonObject NotImplemented");
        } else if (K.f(bVar, JsonNull.INSTANCE)) {
            return null;
        } else {
            throw new RuntimeException();
        }
    }

    public static final a toJsonArray(Object[] objArr) {
        K.n(objArr, "<this>");
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object jsonElement : objArr) {
            arrayList.add(toJsonElement(jsonElement));
        }
        return new a(arrayList);
    }

    public static final kotlinx.serialization.json.b toJsonElement(Object obj) {
        o oVar;
        if (obj instanceof Number) {
            Number number = (Number) obj;
            E3.K k5 = i.f635a;
            if (number == null) {
                return JsonNull.INSTANCE;
            }
            oVar = new o(number, false);
        } else if (obj instanceof Boolean) {
            Boolean bool = (Boolean) obj;
            E3.K k6 = i.f635a;
            if (bool == null) {
                return JsonNull.INSTANCE;
            }
            oVar = new o(bool, false);
        } else if (!(obj instanceof String)) {
            return obj instanceof Object[] ? toJsonArray((Object[]) obj) : obj instanceof Iterable ? toJsonArray((Iterable<?>) (Iterable) obj) : obj instanceof Map ? toJsonObject((Map) obj) : obj instanceof kotlinx.serialization.json.b ? (kotlinx.serialization.json.b) obj : JsonNull.INSTANCE;
        } else {
            String str = (String) obj;
            E3.K k7 = i.f635a;
            if (str == null) {
                return JsonNull.INSTANCE;
            }
            oVar = new o(str, true);
        }
        return oVar;
    }

    public static final c toJsonObject(Map<?, ?> map) {
        K.n(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(C0110h.B(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(C0110h.B(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), toJsonElement(entry2.getValue()));
        }
        return new c(linkedHashMap2);
    }

    public static final a toJsonArray(Iterable<?> iterable) {
        K.n(iterable, "<this>");
        ArrayList arrayList = new ArrayList(l.L0(iterable, 10));
        for (Object jsonElement : iterable) {
            arrayList.add(toJsonElement(jsonElement));
        }
        return new a(arrayList);
    }

    public static final Object parseValue(d dVar) {
        K.n(dVar, "<this>");
        if (dVar.i()) {
            return dVar.h();
        }
        E3.K k5 = i.f635a;
        if (I.b(dVar.h()) != null) {
            Boolean b5 = I.b(dVar.h());
            if (b5 != null) {
                return b5;
            }
            throw new IllegalStateException(dVar + " does not represent a Boolean");
        } else if (r3.i.G(dVar.h()) != null) {
            return Integer.valueOf(Integer.parseInt(dVar.h()));
        } else {
            if (r3.i.H(dVar.h()) != null) {
                return Long.valueOf(Long.parseLong(dVar.h()));
            }
            String h5 = dVar.h();
            K.n(h5, "<this>");
            Float f5 = null;
            try {
                if (f.f8944a.a(h5)) {
                    f5 = Float.valueOf(Float.parseFloat(h5));
                }
            } catch (NumberFormatException unused) {
            }
            if (f5 != null) {
                return Float.valueOf(Float.parseFloat(dVar.h()));
            }
            if (r3.i.F(dVar.h()) != null) {
                return Double.valueOf(Double.parseDouble(dVar.h()));
            }
            return dVar.h();
        }
    }
}
