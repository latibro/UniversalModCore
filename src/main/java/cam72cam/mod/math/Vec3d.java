package cam72cam.mod.math;

import net.minecraft.util.Vec3;

public class Vec3d {
    public static final Vec3d ZERO = new Vec3d(0,0,0);
    public final Vec3 internal;
    public final double x;
    public final double y;
    public final double z;

    public Vec3d(Vec3 internal) {
        this.internal = internal;
        this.x = internal.xCoord;
        this.y = internal.yCoord;
        this.z = internal.zCoord;
    }

    public Vec3d(double x, double y, double z) {
        this(Vec3.createVectorHelper(x, y, z));
    }

    public Vec3d(Vec3i pos) {
        this(pos.x, pos.y, pos.z);
    }

    public Vec3d add(double x, double y, double z) {
        return new Vec3d(internal.addVector(x, y, z));
    }

    public Vec3d add(Vec3i offset) {
        return new Vec3d(internal.addVector(offset.x, offset.y, offset.z));
    }

    public Vec3d add(Vec3d other) {
        return new Vec3d(internal.addVector(other.x, other.y, other.z));
    }

    public Vec3d subtract(Vec3d other) {
        return new Vec3d(internal.subtract(other.internal));
    }

    public Vec3d subtract(Vec3i offset) {
        return subtract(offset.x, offset.y, offset.z);
    }

    public Vec3d subtract(double x, double y, double z) {
        return new Vec3d(internal.subtract(Vec3.createVectorHelper(x, y, z)));
    }

    public double length() {
        return internal.lengthVector();
    }

    public double distanceTo(Vec3d other) {
        return internal.distanceTo(other.internal);
    }

    public Vec3d scale(double scale) {
        return new Vec3d(x * scale, y * scale, z * scale);
    }

    public Vec3d normalize() {
        return new Vec3d(internal.normalize());
    }

    public Vec3d min(Vec3d other) {
        return new Vec3d(Math.min(x, other.x), Math.min(y, other.y), Math.min(z, other.z));
    }

    public Vec3d max(Vec3d other) {
        return new Vec3d(Math.max(x, other.x), Math.max(y, other.y), Math.max(z, other.z));
    }

    @Override
    public String toString() {
        return internal.toString();
    }
}
