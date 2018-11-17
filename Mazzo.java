import java.util.*;
public class Mazzo {
	public static final int CARTE_PER_SEME = 10;
	public static final int NUMERO_CARTE=40;
	private ArrayList<Carta> mazzoOrdinato=new ArrayList<Carta>();
	public ArrayList<Carta> mazzoMescolato=new ArrayList<Carta>();
	private Random r=new Random();
	
	public void stampaMazzo() {
		for(int i=0;i<mazzoMescolato.size();i++)
			System.out.println(mazzoMescolato.get(i).getCarta());
	}
	private Carta assegnaTipi(Carta carta,String tipo) { //assegna un tipo alla carta
		return new Carta(carta.getCarta()+" "+tipo);
	}
	public void ordinaMazzo() { //genera un mazzo ordinato
		for(int i=0;i<NUMERO_CARTE;i+=CARTE_PER_SEME) { //procedi di 10 in 10 in modo da passare tutti i semi della briscola
			for(int j=0;j<CARTE_PER_SEME;j++) {
				switch(i/10) {
				case 0: mazzoOrdinato.add(j, assegnaTipi(new Carta(Carta.valoriCarte[j]),"bastoni")); break; //creo l'arrayList di 
				case 1: mazzoOrdinato.add(i+j, assegnaTipi(new Carta(Carta.valoriCarte[j]),"spade")); break; //oggetti di tipo
				case 2: mazzoOrdinato.add(i+j, assegnaTipi(new Carta(Carta.valoriCarte[j]),"coppe")); break; // carta
				case 3: mazzoOrdinato.add(i+j, assegnaTipi(new Carta(Carta.valoriCarte[j]),"denari")); break;
				}
			}
		}
	}
	public void mescolaMazzo() {
		for(int i=0;i<NUMERO_CARTE;i++) { //resta nel numero di carte di un mazzo
			int indice=Math.abs(r.nextInt()%mazzoOrdinato.size()); //estrai un indice a caso
			mazzoMescolato.add(mazzoOrdinato.get(indice));
			mazzoOrdinato.remove(indice);
		}
	}
	public Carta estraiCarta() {
		if(mazzoMescolato.size()!=0)
		{
			int indice=Math.abs(r.nextInt()%mazzoMescolato.size());
			return mazzoMescolato.get(indice);
		}
		return null;
	}
	public void eliminaCarta(Carta c) {
		int i;
		for(i=0;i<mazzoMescolato.size();i++) {
			if(c.getCarta().equals(mazzoMescolato.get(i).getCarta())) {
				mazzoMescolato.remove(i);
				return;
			}
		}
		return;
	}
	public int quanteCarteMazzo() {
		return mazzoMescolato.size();
	}

}
