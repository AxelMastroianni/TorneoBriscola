import java.util.*;
public class Partita {
	public static final int PRIMA_ESTRAZIONE=3;
	private Giocatore giocatore1=null;
	private Giocatore giocatore2=null;
	private Mazzo mazzo;
	private Random r=new Random();
	private Carta briscola=null;
	private ArrayList<Giocatore> perdenti=new ArrayList<Giocatore>();
	public Partita(Giocatore giocatore1, Giocatore giocatore2, Mazzo mazzo) {
		this.mazzo=mazzo;
		this.giocatore1=giocatore1;
		this.giocatore2=giocatore2;
		briscola=mazzo.estraiCarta();
		mazzo.eliminaCarta(briscola);
	}
	public Carta getBriscola() {
		return briscola;
	}
	//inizio partita
	public void daiLePrimeCarte() {
		//dai le carte al primo giocatore
		for(int i=0;i<PRIMA_ESTRAZIONE;i++) {
			giocatore1.daiCarta(mazzo.estraiCarta());
			mazzo.eliminaCarta(giocatore1.getCarta(i));
		}
		//dai le carte al secondo giocatore
		for(int i=0;i<PRIMA_ESTRAZIONE;i++) {
			giocatore2.daiCarta(mazzo.estraiCarta());
			mazzo.eliminaCarta(giocatore2.getCarta(i));
		}	
	}
	public void pesca(Giocatore g) {
		if(!mazzo.mazzoMescolato.isEmpty()) {
			g.daiCarta(mazzo.estraiCarta());
			mazzo.eliminaCarta(g.getCarta(2));//si da per scontato che la carta estratta vada ad occupare la posizione due in mano
		}
		else
			g.daiCarta(briscola);
	}
	public boolean isBriscola(Carta c) {
		if((c.getFigura()).equals(briscola.getFigura()))
			return true;
		return false;
	}
	public boolean chiPrende(Carta c1, Carta c2) {
		String figura1=c1.getFigura();
		String figura2=c2.getFigura();
		boolean isBriscola1=isBriscola(c1); //evito tante iterazioni
		boolean isBriscola2=isBriscola(c2);
		if(figura1.equals(figura2)) { //se hanno la stessa figura
			if(c1.cercaCarta()>c2.cercaCarta()) 
					return true; //prende c1
			else 
				return false; //prende c2
		}
		else {
			if(isBriscola1 || isBriscola2) {
				if(isBriscola1 && !isBriscola2)//valuta solo briscola e non, non e briscola
					return true; //prende c1
				else
					return false; //prende c2 perché è la briscola
			}
			else
				return true; //non sono né uguali e una di loro non è la briscola, quindi prende il primo che gioca
		}
	}
	public int skippa() {
		int punteggio=Math.abs((r.nextInt()%120));
		giocatore1.setPunteggio(punteggio);
		return giocatore1.getPunteggio();
	}
	public boolean vittoria() {
		if(giocatore1.getPunteggio()>giocatore2.getPunteggio())
			return true;
		return false;
	}
	public void aggiungiPerdente(Giocatore g) {
		perdenti.add(g);
	}
	public void eliminaPerdenti(ArrayList<Giocatore> giocatori) {
		for(int i=0, j=0;i<giocatori.size();i++) {
			if(giocatori.get(i).getNickName().equals(perdenti.get(j).getNickName())) {
				giocatori.remove(i);
				j++;
			}
		}
	}
	public void svuotaPerdenti() {
		for(int i=0;i<perdenti.size();i++)
			perdenti.remove(i);
	}
}
