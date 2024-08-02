data class Vector2(var x: Float, var y: Float) {
    operator fun plus(other: Vector2): Vector2 {
        return Vector2(x + other.x, y + other.y)
    }

    operator fun minus(other: Vector2): Vector2 {
        return Vector2(x - other.x, y - other.y)
    }

    operator fun times(scalar: Float): Vector2 {
        return Vector2(x * scalar, y * scalar)
    }

    operator fun div(scalar: Float): Vector2 {
        return Vector2(x / scalar, y / scalar)
    }

    public override fun toString(): String {
        return "Vector2($x, $y)"
    }

    private fun lerp(start: Float, end: Float, t: Float): Float {
        return start + (end - start) * t
    }

    fun lerp(other: Vector2, t: Float): Vector2 {
        return Vector2(lerp(x, other.x, t), lerp(y, other.y, t))
    }
}
