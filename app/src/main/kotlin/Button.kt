import processing.core.PApplet

class Button(
    private val pApplet: PApplet,
    private val x: Float,
    private val y: Float,
    private val width: Float,
    private val height: Float,
    private val label: String,
    private val onClick: () -> Unit
) {
    fun draw() {
        pApplet.fill(200)
        pApplet.rect(x, y, width, height)
        pApplet.fill(0)
        pApplet.text(label, x + 10, y + height / 2 + 5)
    }

    fun isMouseOver(): Boolean {
        return pApplet.mouseX > x && pApplet.mouseX < x + width &&
                pApplet.mouseY > y && pApplet.mouseY < y + height
    }

    fun handleMousePressed() {
        if (isMouseOver()) {
            onClick()
        }
    }
}
