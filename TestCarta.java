import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCarta {

	@Test
	void testValoreCarta() {
		Carta c=new Carta("tre bastoni");
		assertEquals(10, c.valoreCarta());
	}

	@Test
	void testCercaCarta() {
		Carta c = new Carta("donna denari");
		assertEquals(5,c.cercaCarta());
	}

	@Test
	void testGetFigura() {
		Carta c = new Carta("asso spade");
		assertEquals("spade", c.getFigura());
	}

}
