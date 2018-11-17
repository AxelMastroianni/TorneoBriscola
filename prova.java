import java.util.*;
public class prova {
	public static final String[] GIOCATORI=new String[]{"Darkness","Megumin","Aqua","Kazuma","Masamune","Angelo","Emma","Taki","Mitsuha",
			"Umberto Miletto","Ichigo","Araragi","Tito","Peppino","Senjougahara","Sagiri","Hachikuji","Kirito","Asuna","Aizen","Sara",
			"Rias-senpai","Issei", "Ulqiorra","Grimmjow","Shinobu","Nadeko","Hanekawa","Denis","Simone","Axel","Batman"};
	public static final int PUNTI_MASSIMI=120;
	public static void gestisciMano1(Giocatore g1, Giocatore g2, Carta c1, Carta c2, Partita p) {
		System.out.println("Prende "+g1.getNickName());
		g1.setPunteggio(g1.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
		System.out.println(g1.getPunteggio());
		p.pesca(g1); p.pesca(g2);
		c1=g1.gioca(); c2=g2.gioca();
		g1.eliminaDaMano(c1);
		g2.eliminaDaMano(c2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner tastiera=new Scanner(System.in);
			int k=0, j=0;
			int conta=0;
			String risposta="";
			Random r=new Random();
			ArrayList<Giocatore> partecipantiOrdinati=new ArrayList<Giocatore>();
			ArrayList<Giocatore> partecipantiMescolati=new ArrayList<Giocatore>();
			ArrayList<Giocatore> perdenti=new ArrayList<Giocatore>();
			for(int i=0;i<GIOCATORI.length;i++) {
				partecipantiOrdinati.add(new Giocatore(GIOCATORI[i]));
			}
			for(int i=0;i<GIOCATORI.length;i++) {
				int indice=Math.abs(r.nextInt()%partecipantiOrdinati.size());
				partecipantiMescolati.add(partecipantiOrdinati.get(indice));
				partecipantiOrdinati.remove(indice);
			}
			System.out.println("INIZIA IL TORNEO DI BRISCOLA!");
			while(partecipantiMescolati.size()!=1) {
				System.out.println("Fase "+(j+1));
			while(conta<partecipantiMescolati.size()) {
				Mazzo m=new Mazzo();
				m.ordinaMazzo();
				m.mescolaMazzo();
				Giocatore g1=partecipantiMescolati.get(conta);
				Giocatore g2=partecipantiMescolati.get(conta+1);
				Partita p=new Partita(g1,g2,m);
				System.out.println("Inizia la partita tra "+g1.getNickName()+" e "+g2.getNickName());
				System.out.println("La briscola è: "+p.getBriscola().getCarta());
				System.out.println("Vuoi skippare la partita? "); risposta=tastiera.next();
				if(risposta.equalsIgnoreCase("si")) {
					g1.setPunteggio(p.skippa());
					g2.setPunteggio(PUNTI_MASSIMI-g1.getPunteggio());
					if(p.vittoria()) {
						System.out.println("Ha vinto "+g1.getNickName());//vince g1
						System.out.println("Con un punteggio di "+g1.getPunteggio());
						p.aggiungiPerdente(g2);
					}
					else {
						System.out.println("Ha vinto "+g2.getNickName());// vince g2
						System.out.println("Con un punteggio di "+g2.getPunteggio());
						p.aggiungiPerdente(g1);
					}
				}
				else {
					p.daiLePrimeCarte();
					Carta c1=new Carta(g1.gioca().getCarta());
					Carta c2=new Carta(g2.gioca().getCarta());
					g1.eliminaDaMano(c1); g2.eliminaDaMano(c2);
					int tracciatore=1; //parte dal giocatore 1
					while(!m.mazzoMescolato.isEmpty())
					{
						System.out.println(c1.getCarta()+"   "+c2.getCarta());
						if(p.chiPrende(c1, c2) && tracciatore==1){
							System.out.println("Prende "+g1.getNickName());
							g1.setPunteggio(g1.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
							p.pesca(g1); p.pesca(g2);
							c1=g1.gioca(); c2=g2.gioca();
							g1.eliminaDaMano(c1);
							g2.eliminaDaMano(c2);
						}
						else if(p.chiPrende(c2, c1)) {
							tracciatore=2;
							System.out.println("Prende "+g2.getNickName());
							g2.setPunteggio(g2.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
							p.pesca(g2); p.pesca(g1);
							c2=g2.gioca(); c1=g1.gioca();
							g2.eliminaDaMano(c2); 
							g1.eliminaDaMano(c1);
						}
						else
							{tracciatore = 1; k--;}
						System.out.println(g1.getPunteggio()+"  "+g2.getPunteggio());
					}
					System.out.println("Manche Finale");
					while(!g1.carteInMano.isEmpty() || !g2.carteInMano.isEmpty()) {
						System.out.println(c1.getCarta()+"   "+c2.getCarta());
						if(p.chiPrende(c1, c2) && tracciatore==1){
							System.out.println("Prende "+g1.getNickName());
							g1.setPunteggio(g1.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
							c1=g1.gioca(); c2=g2.gioca();
							g1.eliminaDaMano(c1);
							g2.eliminaDaMano(c2);
						}
						else if(p.chiPrende(c2, c1)) {
							tracciatore=2;
							System.out.println("Prende "+g2.getNickName());
							g2.setPunteggio(g2.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
							c2=g2.gioca(); c1=g1.gioca();
							g2.eliminaDaMano(c2);
							g1.eliminaDaMano(c1);
						}
						else
							tracciatore = 1;
						System.out.println(g1.getPunteggio()+"  "+g2.getPunteggio());
					}
					if(p.chiPrende(c1, c2) && tracciatore==1) {
						System.out.println("Prende "+g1.getNickName());
						g1.setPunteggio(g1.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
					}
					else {
						System.out.println("Prende "+g2.getNickName());
						g2.setPunteggio(g2.aumentaPunteggio(c1.valoreCarta()+c2.valoreCarta()));
					}
					System.out.println("Situazione: "+g1.getNickName()+": "+g1.getPunteggio());
					System.out.println(g2.getNickName()+": "+g2.getPunteggio());
					if(p.vittoria()) {
						System.out.println("Ha vinto il giocatore "+g1.getNickName());
						System.out.println("Con un punteggio di "+g1.getPunteggio());
						perdenti.add(g2);
					}
					else {
						System.out.println("Ha vinto il giocatore "+g2.getNickName());
						System.out.println("Con un punteggio di "+g2.getPunteggio());
						perdenti.add(g1);
					}
				}
				conta+=2;
				System.out.println("Premi una lettera per continuare: "); risposta=tastiera.next();
			}
			j++;
			conta=0;
			for(int i=0;i<perdenti.size();i++)
				partecipantiMescolati.remove(perdenti.get(i));
			System.out.println("Restano i seguenti partecipanti: ");
			for(int i=0;i<partecipantiMescolati.size();i++) {
				System.out.println(partecipantiMescolati.get(i).getNickName());
				partecipantiMescolati.get(i).setPunteggio(0);
			}
			}
			System.out.println("Il vincitore del torneo è: "+partecipantiMescolati.get(0).getNickName());
				
	}
}
