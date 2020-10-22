package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Siabreu
 */
public class Cena implements GLEventListener {

    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private TextRenderer textRenderer;
    public float dia, ano, aux, anguloy, anguloz;

    public int mode, shader, controleLuz;
    private GLU glu;

    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        GL2 gl = drawable.getGL().getGL2();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;

        anguloy = 1;
        anguloz = 0;
        aux = 50;
        controleLuz = 1;
        mode = GL2.GL_FILL;
        shader = GL2.GL_FLAT;
        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 15));

        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        //objeto para desenho 3D
        GLUT glut = new GLUT();
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 0);
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glLoadIdentity(); //ler a matriz identidade

        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);

        String m = mode == GL2.GL_LINE ? "LINE" : "FILL";
        
        ano = (ano + aux * 0.005f);
        dia = (dia + aux * 0.01f);
        dadosObjeto(gl, 20, 580, Color.WHITE, "Modo: " + m);


        if (controleLuz == 1) {
            float[] posLuz = {0f, 0f, 0f, 1};
            float[] corLuz = {1f, 1f, 1f, 1};

            gl.glEnable(GL2.GL_LIGHTING);
            gl.glEnable(GL2.GL_LIGHT0);
            gl.glEnable(GL2.GL_COLOR_MATERIAL);

            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, corLuz, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posLuz, 0);

        } else {
            gl.glDisable(GL2.GL_LIGHTING);
        }

        gl.glShadeModel(shader);
        gl.glPushMatrix();
        gl.glColor3f(0.7f, 0.7f, 0);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glRotatef(dia, 0f, 0f, 1f);
        glut.glutSolidSphere(11, 20, 16);
        gl.glPopMatrix();
        
        
        
        //Mercurio
        gl.glPushMatrix();
        gl.glRotatef(ano*4.5f, 0, anguloy, anguloz);
        gl.glTranslatef(20f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.1f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0.4f, 0.4f, 0.4f);
        glut.glutSolidSphere(0.8f, 10, 8);
        gl.glPopMatrix();
        
        
        //Venus
        gl.glPushMatrix();
        gl.glRotatef(ano*1.5f, 0, anguloy, anguloz);
        gl.glTranslatef(25f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.2f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0.4f, 0.4f, 0);
        glut.glutSolidSphere(1.2f, 10, 8);
        gl.glPopMatrix();

        
        //Terra
        gl.glPushMatrix();
        gl.glRotatef(ano, 0, anguloy, anguloz);
        gl.glTranslatef(30f, 0.0f, 0.0f);
        gl.glRotatef(dia, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0f, 0f, 0.7f);
        glut.glutSolidSphere(1.2f, 10, 8);
        gl.glPopMatrix();

        //Marte
        gl.glPushMatrix();
        gl.glRotatef(ano*0.5f, 0, anguloy, anguloz);
        gl.glTranslatef(45f, 0.0f, 0.0f);
        gl.glRotatef(dia, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0.7f, 0, 0);
        glut.glutSolidSphere(0.8f, 10, 8);
        gl.glPopMatrix();

        //Jupiter
        gl.glPushMatrix();
        gl.glRotatef(ano*0.08f, 0, anguloy, anguloz);
        gl.glTranslatef(55f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.1f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0.6f, 0.3f, 0);
        glut.glutSolidSphere(5, 10, 8);
        gl.glPopMatrix();

        //Saturno
        gl.glPushMatrix();
        gl.glRotatef(ano*0.04f, 0, anguloy, anguloz);
        gl.glTranslatef(70f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.5f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0.4f, 0.4f, 0);
        glut.glutSolidSphere(4, 10, 8);
        gl.glPopMatrix();

        //Uranus
        gl.glPushMatrix();
        gl.glRotatef(ano*0.001f, 0, anguloy, anguloz);
        gl.glTranslatef(84f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.8f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0f, 0f, 0.4f);
        glut.glutSolidSphere(1.5, 10, 8);
        gl.glPopMatrix();

        //Netuno
        gl.glPushMatrix();
        gl.glRotatef(ano*0.0005f, 0, anguloy, anguloz);
        gl.glTranslatef(95f, 0.0f, 0.0f);
        gl.glRotatef(dia*0.8f, 0f, 1f, 0f);
        gl.glRotatef(90, anguloy, 0, anguloz);
        gl.glColor3f(0f, 0f, 0.2f);
        glut.glutSolidSphere(1.5f, 10, 8);
        gl.glPopMatrix();
        
        dadosObjeto(gl, 20, 20, Color.WHITE, "r - para alterar o angulo");
        dadosObjeto(gl, 20, 35, Color.WHITE, "e - para ativar/desativar a iluminação");
        dadosObjeto(gl, 20, 50, Color.WHITE, "w - para alterar a tonalização");
        dadosObjeto(gl, 20, 65, Color.WHITE, "q - para alterar o modo de exibição");
        dadosObjeto(gl, 20, 5, Color.WHITE, "utilize as setas horizontais para acelerar/desacelerar");

        gl.glFlush();
    }

    public void desenharCarro(GL2 gl, GLUT glut) {

    }

    public void dadosObjeto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase) {
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, mode);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();

        //evita a divisao por zero
        if (height == 0) {
            height = 1;
        }
        //calcula a proporcao da janela (aspect ratio) da nova janela
        //float aspect = (float) width / height;

        //seta o viewport para abranger a janela inteira
        //gl.glViewport(0, 0, width, height);
        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //ler a matriz identidade

        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
//        if(width >= height)            
//            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
//        else        
//            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
