package br.com.porquesim.eventmanager;


public class Main {

	public Main() {

		EventManager.get().registerListener("teste", new EventListener() {

			@Override
			public void on(String evt) {

				System.out.println("Received event: " + evt);

			}

		});

		EventManager.get().fire("teste");

		//manager.fire(new TesteEvent(this));

	}

	@ListenTo(event = TesteEvent.class)
	public void faz(TesteEvent ev) {

		System.out.println("Fazendo");

	}

	public static void main(String[] args) {

		new Main();
		
	}

}
