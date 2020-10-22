package input;

import cena.Cena;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;

/**
 *
 * @author Siabreu
 */
public class KeyBoard implements KeyListener {

    private Cena cena;

    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyChar() == 'q') {
            if (cena.mode == GL2.GL_FILL) {
                cena.mode = GL2.GL_LINE;
            } else {
                cena.mode = GL2.GL_FILL;
            }

        }
        if (e.getKeyChar() == 'w') {
            if (cena.shader == GL2.GL_FLAT) {
                cena.shader = GL2.GL_SMOOTH;
            } else {
                cena.shader = GL2.GL_FLAT;
            }

        }
        if (e.getKeyChar() == 'e') {
            if(cena.controleLuz == 1){
                cena.controleLuz = 0;
            } else {
                cena.controleLuz = 1;
            }
        }
        if (e.getKeyChar() == 'r') {
            if(cena.anguloz == 0){
                cena.anguloy = 0;
                cena.anguloz = 1;
            } else {
                cena.anguloy = 1;
                cena.anguloz = 0;
            }
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
               
                break;
            case KeyEvent.VK_DOWN:
                
                break;
            case KeyEvent.VK_RIGHT:
                cena.aux*=2f;
                break;
            case KeyEvent.VK_LEFT:
                cena.aux*=0.5f;
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
