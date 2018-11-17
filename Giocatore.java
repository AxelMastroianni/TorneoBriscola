import java.util.*;
public class Giocatore {
	public static final int CARTE_IN_MANO=3;
	private String nickName="";
	private int punteggio=0;
	public ArrayList<Carta> carteInMano=new ArrayList<Carta>();
	private Random r=new Random();
	public Giocatore(String nickName) {
		this.nickName=nickName;
	}
	
	public int getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(int punteggio) {
		this.punteggio=punteggio;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int aumentaPunteggio(int punti) {
		punteggio+=punti;
		return punteggio;
	}
	public Carta gioca() {
		if(quanteInMano()==CARTE_IN_MANO) {
			int indice=Math.abs(r.nextInt()%CARTE_IN_MANO);
			return carteInMano.get(indice);
		}
		else if(quanteInMano()==CARTE_IN_MANO-1) {
			int indice=Math.abs(r.nextInt()%(CARTE_IN_MANO-1));
			return carteInMano.get(indice);
		}
		else
			return carteInMano.get(0);
	}
	public void eliminaDaMano(Carta c) {
		int i;
		for(i=0;i<quanteInMano();i++) {
			if(c.getCarta().equals(carteInMano.get(i).getCarta())) {
				carteInMano.remove(i);
				break;
			}
		}
	}
	public void daiCarta(Carta c) {
		carteInMano.add(c);
	}
	public Carta getCarta(int indice) {
		return carteInMano.get(indice);
	}
	public void vediMano() {
		for(int i=0;i<carteInMano.size();i++)
			System.out.println(carteInMano.get(i).getCarta());
	}
	public int quanteInMano() {
		return carteInMano.size();
	}
}
