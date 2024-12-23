package com.ldts.t14g01.Tenebris.utils;

public class HitBox extends Pair<Vector2D> {
    // First is the position of the upper left corner relative to the element position
    // Second is the position of the lower right corner relative to the upper left corner

    public HitBox(Vector2D first, Vector2D second) {
        super(first, second);
    }

    public static boolean collide(Vector2D aPosition, HitBox aHitBox, Vector2D bPosition, HitBox bHitBox) {
        int a1x = aPosition.x() + aHitBox.first.x();
        int a1y = aPosition.y() + aHitBox.first.y();
        int b1x = bPosition.x() + bHitBox.first.x();
        int b1y = bPosition.y() + bHitBox.first.y();
        int a2x = a1x + aHitBox.second.x();
        int a2y = a1y + aHitBox.second.y();
        int b2x = b1x + bHitBox.second.x();
        int b2y = b1y + bHitBox.second.y();

        if (a2x < b1x) return false;
        if (a1x > b2x) return false;
        if (a2y < b1y) return false;
        return a1y <= b2y;
    }
}
