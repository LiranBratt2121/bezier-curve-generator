import processing.core.PApplet

class App : PApplet() {
    val bezier = BezierCurve()
    val buttons = bezier.generateButtons(this)

    override fun settings() {
        size(800, 600)
    }

    override fun mousePressed() {
        buttons.forEach { it.handleMousePressed() }
        if (!buttons.any { it.isMouseOver() }) {
            bezier.addPoint(mouseX.toFloat(), mouseY.toFloat())
        }
    }

    override fun setup() {
        background(255)
    }

    override fun draw() {
        // Clear the screen.
        background(255)
        // Draw buttons.
        bezier.drawButtons(this, buttons)
        // Get new curve points to draw based on the selected type.
        bezier.getCurve()
        // Draw the line points. 
        bezier.drawLinePoints(this)
        // Draw the control points.
        bezier.drawPressedPoints(this)
    }
}

fun main() {
    PApplet.main("App")
}
