package com.hawkshaw.library.deviceinfo;

import W1.g;
import W1.m;

public final class Shell {
    public static final Shell INSTANCE = new Shell();
    private static m shell = g.b();

    static {
        m.f2372j.getClass();
    }

    private Shell() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.util.concurrent.CountDownLatch} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: W1.k} */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object, j3.q] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String runShellCommand(java.lang.String r27) {
        /*
            r26 = this;
            r0 = r27
            java.lang.String r1 = "command"
            Y1.K.n(r0, r1)
            W1.m r1 = shell
            r1.getClass()
            java.lang.Process r1 = r1.f2382i     // Catch:{ IllegalThreadStateException -> 0x001c }
            r1.exitValue()     // Catch:{ IllegalThreadStateException -> 0x001c }
            W1.g r1 = W1.m.f2372j
            r1.getClass()
            W1.m r1 = W1.g.b()
            shell = r1
        L_0x001c:
            W1.m r9 = shell
            java.util.UUID r10 = java.util.UUID.randomUUID()
            java.lang.String r1 = "randomUUID()"
            Y1.K.m(r10, r1)
            W1.b r11 = W1.b.f2340F
            W1.b r12 = W1.b.f2339E
            W1.a r6 = W1.a.f2335E
            W1.a r7 = W1.a.f2336F
            W1.c r13 = new W1.c
            r3 = 0
            r8 = 1
            r1 = r13
            r2 = r10
            r4 = r11
            r5 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            java.lang.String r14 = "$(exit "
            java.lang.String r15 = "echo '"
            java.lang.String r1 = "echo '"
            monitor-enter(r9)
            Q0.b r2 = r9.f2381h     // Catch:{ all -> 0x0123 }
            W1.i r3 = W1.i.f2361c     // Catch:{ all -> 0x0123 }
            boolean r2 = Y1.K.f(r2, r3)     // Catch:{ all -> 0x0123 }
            if (r2 != 0) goto L_0x01b4
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0123 }
            r2.<init>()     // Catch:{ all -> 0x0123 }
            java.util.List r8 = java.util.Collections.synchronizedList(r2)     // Catch:{ all -> 0x0123 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0123 }
            r2.<init>()     // Catch:{ all -> 0x0123 }
            java.util.List r7 = java.util.Collections.synchronizedList(r2)     // Catch:{ all -> 0x0123 }
            W1.k r6 = new W1.k     // Catch:{ all -> 0x0123 }
            r5 = 2
            r6.<init>(r5)     // Catch:{ all -> 0x0123 }
            j3.q r4 = new j3.q     // Catch:{ all -> 0x0123 }
            r4.<init>()     // Catch:{ all -> 0x0123 }
            r2 = 256(0x100, float:3.59E-43)
            r4.f7498D = r2     // Catch:{ all -> 0x0123 }
            androidx.lifecycle.k0 r2 = new androidx.lifecycle.k0     // Catch:{ all -> 0x0123 }
            r3 = 1
            r2.<init>(r10, r4, r6, r3)     // Catch:{ all -> 0x0123 }
            java.util.concurrent.locks.ReentrantLock r16 = new java.util.concurrent.locks.ReentrantLock     // Catch:{ all -> 0x0123 }
            r16.<init>()     // Catch:{ all -> 0x0123 }
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ all -> 0x0123 }
            r17.<init>()     // Catch:{ all -> 0x0123 }
            r18 = r14
            java.util.List r14 = java.util.Collections.synchronizedList(r17)     // Catch:{ all -> 0x0123 }
            W1.j r3 = r9.f2379f     // Catch:{ all -> 0x0123 }
            r3.getClass()     // Catch:{ all -> 0x0123 }
            r3.f2365F = r2     // Catch:{ all -> 0x0123 }
            W1.j r3 = r9.f2380g     // Catch:{ all -> 0x0123 }
            r3.getClass()     // Catch:{ all -> 0x0123 }
            r3.f2365F = r2     // Catch:{ all -> 0x0123 }
            W1.j r3 = r9.f2379f     // Catch:{ all -> 0x0123 }
            java.lang.String r2 = "stdout"
            Y1.K.m(r8, r2)     // Catch:{ all -> 0x0123 }
            java.util.LinkedHashSet r2 = r9.f2376c     // Catch:{ all -> 0x0123 }
            r19 = r1
            W1.l r1 = new W1.l     // Catch:{ all -> 0x0123 }
            r20 = r2
            r2 = r1
            r17 = r3
            r21 = r15
            r15 = 1
            r3 = r13
            r22 = r4
            r4 = r8
            r23 = r5
            r5 = r14
            r24 = r6
            r6 = r20
            r15 = r7
            r7 = r16
            r25 = r8
            r8 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0123 }
            r17.getClass()     // Catch:{ all -> 0x0123 }
            r2 = r17
            r2.f2364E = r1     // Catch:{ all -> 0x0123 }
            W1.j r8 = r9.f2380g     // Catch:{ all -> 0x0123 }
            java.lang.String r1 = "stderr"
            Y1.K.m(r15, r1)     // Catch:{ all -> 0x0123 }
            java.util.LinkedHashSet r5 = r9.f2377d     // Catch:{ all -> 0x0123 }
            W1.l r11 = new W1.l     // Catch:{ all -> 0x0123 }
            r7 = r19
            r1 = r11
            r2 = r13
            r3 = r15
            r4 = r14
            r6 = r16
            r16 = r15
            r15 = r7
            r7 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0123 }
            r8.getClass()     // Catch:{ all -> 0x0123 }
            r8.f2364E = r11     // Catch:{ all -> 0x0123 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0123 }
            r1 = 0
            W1.i r2 = W1.i.f2360b     // Catch:{ InterruptedException -> 0x0129 }
            r9.f2381h = r2     // Catch:{ InterruptedException -> 0x0129 }
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ InterruptedException -> 0x0129 }
            r2[r1] = r0     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0129 }
            r3.<init>(r15)     // Catch:{ InterruptedException -> 0x0129 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.String r4 = "' $?"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x0129 }
            r4 = 1
            r2[r4] = r3     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0129 }
            r4 = r21
            r3.<init>(r4)     // Catch:{ InterruptedException -> 0x0129 }
            r3.append(r10)     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.String r4 = "' >&2"
            r3.append(r4)     // Catch:{ InterruptedException -> 0x0129 }
            java.lang.String r3 = r3.toString()     // Catch:{ InterruptedException -> 0x0129 }
            r2[r23] = r3     // Catch:{ InterruptedException -> 0x0129 }
            r9.a(r2)     // Catch:{ InterruptedException -> 0x0129 }
            r24.a()     // Catch:{ InterruptedException -> 0x0129 }
            W1.i r2 = W1.i.f2359a     // Catch:{ all -> 0x0123 }
            r9.f2381h = r2     // Catch:{ all -> 0x0123 }
            r3 = r22
            goto L_0x0138
        L_0x0123:
            r0 = move-exception
            goto L_0x01bc
        L_0x0126:
            r0 = move-exception
            goto L_0x01af
        L_0x0129:
            r2 = 158(0x9e, float:2.21E-43)
            r3 = r22
            r3.f7498D = r2     // Catch:{ all -> 0x0126 }
            i3.a r2 = r13.f2344a     // Catch:{ all -> 0x0126 }
            r2.invoke()     // Catch:{ all -> 0x0126 }
            W1.i r2 = W1.i.f2359a     // Catch:{ all -> 0x0123 }
            r9.f2381h = r2     // Catch:{ all -> 0x0123 }
        L_0x0138:
            int r2 = r3.f7498D     // Catch:{ all -> 0x0123 }
            if (r2 == 0) goto L_0x0159
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ all -> 0x0123 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0123 }
            r5 = r18
            r4.<init>(r5)     // Catch:{ all -> 0x0123 }
            int r5 = r3.f7498D     // Catch:{ all -> 0x0123 }
            r4.append(r5)     // Catch:{ all -> 0x0123 }
            r5 = 41
            r4.append(r5)     // Catch:{ all -> 0x0123 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0123 }
            r2[r1] = r4     // Catch:{ all -> 0x0123 }
            r9.a(r2)     // Catch:{ all -> 0x0123 }
        L_0x0159:
            int r1 = W1.f.f2353f     // Catch:{ all -> 0x0123 }
            java.lang.String r1 = "output"
            Y1.K.m(r14, r1)     // Catch:{ all -> 0x0123 }
            int r6 = r3.f7498D     // Catch:{ all -> 0x0123 }
            r1 = r10
            r2 = r27
            r3 = r25
            r4 = r16
            r5 = r14
            W1.f r0 = C0.b.b(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0123 }
            boolean r1 = r13.f2345b     // Catch:{ all -> 0x0123 }
            if (r1 == 0) goto L_0x0188
            java.util.LinkedHashSet r1 = r9.f2375b     // Catch:{ all -> 0x0123 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0123 }
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0123 }
            if (r2 != 0) goto L_0x017f
            goto L_0x0188
        L_0x017f:
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x0123 }
            E0.C0010a.x(r0)     // Catch:{ all -> 0x0123 }
            r0 = 0
            throw r0     // Catch:{ all -> 0x0123 }
        L_0x0188:
            monitor-exit(r9)
            int r1 = r0.f2357d
            if (r1 != 0) goto L_0x019e
            java.util.List r0 = r0.f2354a
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.lang.String r2 = "\n"
            r4 = 0
            r6 = 62
            r3 = 0
            r5 = 0
            java.lang.String r0 = X2.o.X0(r1, r2, r3, r4, r5, r6)
            goto L_0x01ae
        L_0x019e:
            java.util.List r0 = r0.f2355b
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.lang.String r2 = "\n"
            r4 = 0
            r6 = 62
            r3 = 0
            r5 = 0
            java.lang.String r0 = X2.o.X0(r1, r2, r3, r4, r5, r6)
        L_0x01ae:
            return r0
        L_0x01af:
            W1.i r1 = W1.i.f2359a     // Catch:{ all -> 0x0123 }
            r9.f2381h = r1     // Catch:{ all -> 0x0123 }
            throw r0     // Catch:{ all -> 0x0123 }
        L_0x01b4:
            r1.b r0 = new r1.b     // Catch:{ all -> 0x0123 }
            java.lang.String r1 = "The shell is shutdown"
            r0.<init>(r1)     // Catch:{ all -> 0x0123 }
            throw r0     // Catch:{ all -> 0x0123 }
        L_0x01bc:
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.deviceinfo.Shell.runShellCommand(java.lang.String):java.lang.String");
    }
}
