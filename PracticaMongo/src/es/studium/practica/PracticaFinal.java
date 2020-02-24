package es.studium.practica;

import static com.mongodb.client.model.Filters.eq;
import java.util.Scanner;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class PracticaFinal {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = conexion.getDatabase("harry");
		MongoCollection<Document> personajes = database.getCollection("characters");
		/* Recorremos todos los resultados y los mostramos */
		Scanner lectura = new Scanner(System.in);
		System.out.println("Escriba la opción que quiere seleccionar del 1 al 4");
		int seleccionado = lectura.nextInt();
		Boolean comprobante = false;
		while(!comprobante) {
			switch(seleccionado) {
			case 1:
				System.out.println("------------------------------------------------");
				especieHumano(personajes);
				comprobante=true;
				break;
			case 2:
				System.out.println("------------------------------------------------");
				anterior1979(personajes);
				comprobante=true;
				break;
			case 3:
				System.out.println("------------------------------------------------");
				baritaSagrada(personajes);		
				comprobante=true;
				break;
			case 4:
				System.out.println("------------------------------------------------");
				estudiantesVivos(personajes);
				comprobante=true;
				break;
			default:
				System.out.println("Elija una opción");
				break;
			}
		}
		System.out.println("------------------------------------------------");
		System.out.println("Fin");
	}
	private static void estudiantesVivos(MongoCollection<Document> personajes) {
		FindIterable<Document> busqueda = personajes.find(and(eq("hogwartsStudent", true),eq("alive",true)));
		for (Object personaje : busqueda) {
			System.out.println(((Document) personaje).toJson());
		}		
	}
	private static void baritaSagrada(MongoCollection<Document> personajes) {
		FindIterable<Document> busqueda = personajes.find(eq("wand.wood", "holly"));
		for (Object personaje : busqueda) {
			System.out.println(((Document) personaje).toJson());
		}		
	}
	private static void anterior1979(MongoCollection<Document> personajes) {
		FindIterable<Document> busqueda = personajes.find(lte("yearOfBirth", 1976));
		for (Object personaje : busqueda) {
			System.out.println(((Document) personaje).toJson());
		}
	}
	private static void especieHumano(MongoCollection<Document> personajes) {
		FindIterable<Document> busqueda = personajes.find(eq("species", "human"));
		for (Object personaje : busqueda) {
			System.out.println(((Document) personaje).toJson());
		}		
	}
}