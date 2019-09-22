package cam72cam.mod.entity.boundingbox;

import cam72cam.mod.math.Vec3d;
import cam72cam.mod.math.Vec3i;
import net.minecraft.util.AxisAlignedBB;

public interface IBoundingBox {
    static IBoundingBox from(AxisAlignedBB internal) {
        if (internal == null) {
            return null;
        }
        return new IBoundingBox() {
            @Override
            public Vec3d min() {
                return new Vec3d(internal.minX, internal.minY, internal.minZ);
            }

            @Override
            public Vec3d max() {
                return new Vec3d(internal.maxX, internal.maxY, internal.maxZ);
            }

            @Override
            public IBoundingBox expand(Vec3d centered) {
                return from(internal.addCoord(centered.x, centered.y, centered.z));
            }

            @Override
            public IBoundingBox contract(Vec3d centered) {
                return from(internal.addCoord(-centered.x, -centered.y, -centered.z));
            }

            @Override
            public IBoundingBox grow(Vec3d val) {
                return from(internal.expand(val.x, val.y, val.z));
            }

            @Override
            public IBoundingBox offset(Vec3d vec3d) {
                return from(internal.offset(vec3d.x, vec3d.y, vec3d.z));
            }

            private AxisAlignedBB AABBCtr(Vec3d min, Vec3d max) {
                return AxisAlignedBB.getBoundingBox(min.x, min.y, min.z, max.x, max.y, max.z);
            }

            @Override
            public double calculateXOffset(IBoundingBox other, double offsetX) {
                return internal.calculateXOffset(AABBCtr(other.min(), other.max()), offsetX);
            }

            @Override
            public double calculateYOffset(IBoundingBox other, double offsetY) {
                return internal.calculateYOffset(AABBCtr(other.min(), other.max()), offsetY);
            }

            @Override
            public double calculateZOffset(IBoundingBox other, double offsetZ) {
                return internal.calculateZOffset(AABBCtr(other.min(), other.max()), offsetZ);
            }

            @Override
            public boolean intersects(Vec3d min, Vec3d max) {
                return internal.intersectsWith(AABBCtr(min, max));
            }

            @Override
            public boolean contains(Vec3d vec) {
                return internal.isVecInside(vec.internal);
            }
        };
    }

    static IBoundingBox from(Vec3i pos) {
        return from(AxisAlignedBB.getBoundingBox(pos.x, pos.y, pos.z, pos.x, pos.y, pos.z));
    }

    Vec3d min();

    Vec3d max();

    IBoundingBox expand(Vec3d val);

    IBoundingBox contract(Vec3d val);

    IBoundingBox grow(Vec3d val);

    IBoundingBox offset(Vec3d vec3d);

    double calculateXOffset(IBoundingBox other, double offsetX);

    double calculateYOffset(IBoundingBox other, double offsetY);

    double calculateZOffset(IBoundingBox other, double offsetZ);

    boolean intersects(Vec3d min, Vec3d max);

    boolean contains(Vec3d vec);

    default boolean intersects(IBoundingBox bounds) {
        return this.intersects(bounds.min(), bounds.max());
    }
}
