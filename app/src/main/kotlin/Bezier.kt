import processing.core.PApplet
import Vector2
import Button

enum class BezierType {
    LINEAR,
    QUADRATIC,
    QUBIC
}

class BezierCurve(private val pointInterval: Float = 0.001f) {
    private val points: MutableList<Vector2> = mutableListOf()
    private val linePoints: MutableList<Vector2> = mutableListOf()
    private var bezierType: BezierType = BezierType.LINEAR

    fun generateButtons(pApplet: PApplet): List<Button> {
        val linearButton = Button(pApplet, 10f, 10f, 100f, 30f, "Linear") { bezierType = BezierType.LINEAR }
        val quadraticButton = Button(pApplet, 120f, 10f, 100f, 30f, "Quadratic") { bezierType = BezierType.QUADRATIC }
        val qubicButton = Button(pApplet, 230f, 10f, 100f, 30f, "Qubic") { bezierType = BezierType.QUBIC }
        val clearButton = Button(pApplet, 340f, 10f, 100f, 30f, "Clear") { clearPoints(); clearLinePoints() }
        return listOf(linearButton, quadraticButton, qubicButton, clearButton)
    }

    fun drawButtons(pApplet: PApplet, buttons: List<Button>) {
        buttons.forEach { it.draw() }
        pApplet.fill(0f)
        pApplet.text("Current: $bezierType", 10f, 50f)
    }

    fun drawPressedPoints(pApplet: PApplet) {
        pApplet.stroke(0f)
        pApplet.fill(255f, 0f, 0f)
        points.forEach { pApplet.ellipse(it.x, it.y, 10f, 10f) }
    }

    fun drawLinePoints(pApplet: PApplet) {
        pApplet.stroke(0f)
        pApplet.fill(0f, 0f, 0f)
        linePoints.forEach { pApplet.ellipse(it.x, it.y, 5f, 5f) }
    }

    fun addPoint(x: Float, y: Float) {
        points.add(Vector2(x, y))
    }

    private fun addLinePoint(x: Float, y: Float) {
        linePoints.add(Vector2(x, y))
    }

    private fun clearLinePoints() {
        linePoints.clear()
    }

    private fun clearPoints() {
        points.clear()
    }

    fun getCurve() {
        when (bezierType) {
            BezierType.LINEAR -> drawLiner()
            BezierType.QUADRATIC -> drawQuadratic()
            BezierType.QUBIC -> drawQubic()
        }
    }

    private fun drawLiner() {
        if (points.size < 2) return
        val start = points[0]
        val end = points[1]
        var t = 0.0f
        while (t <= 1.0f) {
            val current = start.lerp(end, t)
            addLinePoint(current.x, current.y)
            t += pointInterval
        }

        clearPoints()
    }

    private fun drawQuadratic() {
        if (points.size < 3) return
        val start = points[0]
        val control = points[1]
        val end = points[2]
        var t = 0.0f
        while (t <= 1.0f) {
            val l0 = start.lerp(control, t)
            val l1 = control.lerp(end, t)
            val current = l0.lerp(l1, t)
            addLinePoint(current.x, current.y)
            t += pointInterval
        }

        clearPoints()
    }

    private fun drawQubic() {
        if (points.size < 4) return
        val start = points[0]
        val control1 = points[1]
        val control2 = points[2]
        val end = points[3]
        var t = 0.0f
        while (t <= 1.0f) {
            val l0 = start.lerp(control1, t)
            val l1 = control1.lerp(control2, t)
            val l2 = control2.lerp(end, t)
            val l3 = l0.lerp(l1, t)
            val l4 = l1.lerp(l2, t)
            val current = l3.lerp(l4, t)
            addLinePoint(current.x, current.y)
            t += pointInterval
        }

        clearPoints()
    }
}
