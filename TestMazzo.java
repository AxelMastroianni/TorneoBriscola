import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMazzo {

	
	@Test
	void testQuanteCarteMazzo() {
		Mazzo m=new Mazzo();
		m.ordinaMazzo();
		m.mescolaMazzo();
		/*while(!m.mazzoMescolato.isEmpty()){
			m.eliminaCarta(m.mazzoMescolato.get(0));
		}*/
		assertEquals(40,m.quanteCarteMazzo());
	}

}
