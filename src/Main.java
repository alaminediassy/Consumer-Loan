
import com.visiplus.consumer_loan.business.Client;
import com.visiplus.consumer_loan.business.Loan;
import com.visiplus.consumer_loan.business.PretService;
import com.visiplus.consumer_loan.util.DateUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PretService pretService = new PretService();

        // Ajout de 5 clients
        Client[] clients = {
                new Client("DIOP", "Khady"),
                new Client("DIASSY", "Fatima"),
                new Client("DRAME", "Madou"),
                new Client("MARONE", "Ibou"),
                new Client("THIAM", "Moustapha")
        };

        while (true) {
            System.out.println("1. Voir tous les prêts triés par montant (du plus élevé au plus petit)");
            System.out.println("2. Voir tous les prêts triés par taux (du plus élevé au plus petit)");
            System.out.println("3. Voir la liste des prêts qui débutent entre deux dates données");
            System.out.println("4. Ajouter un prêt");
            System.out.println("5. Quitter");
            System.out.print("Faites votre choix : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    pretService.getLoanByAmount().forEach(pret -> {
                        System.out.println("Client: " + pret.getClient() + ", Montant: " + pret.getMontantDemande());
                    });
                    break;
                case 2:
                    pretService.getLoanByRate().forEach(pret -> {
                        System.out.println("Client: " + pret.getClient() + ", Taux: " + pret.getMontantMensualite());
                    });
                    break;
                case 3:
                    System.out.print("Entrez les dates séparées par des virgules (MM/yyyy, MM/yyyy) : ");
                    String[] dates = scanner.next().split(",");
                    LocalDate startDateRange = DateUtil.parseDate(dates[0].trim());
                    LocalDate endDateRange = DateUtil.parseDate(dates[1].trim());
                    pretService.getLoanByDateRange(startDateRange, endDateRange).forEach(pret -> {
                        System.out.println("Client: " + pret.getClient() + ", Date: " + pret.getDateEffet());
                    });
                    break;
                case 4:
                    System.out.println("Liste des clients :");
                    for (int i = 0; i < clients.length; i++) {
                        System.out.println(i + ". " + clients[i]);
                    }
                    System.out.print("Veuillez saisir l'id du client concerné : ");
                    int clientId = scanner.nextInt();
                    Client client = clients[clientId];
                    System.out.print("Veuillez saisir le montant demandé : ");
                    double amount = scanner.nextDouble();
                    System.out.print("Veuillez saisir le taux annuel (en pourcentage) : ");
                    double rate = scanner.nextDouble() / 100;
                    System.out.print("Veuillez saisir la durée en mois : ");
                    int months = scanner.nextInt();
                    System.out.print("Veuillez saisir la date d'effet au format MM/yyyy : ");
                    String startDateStr = scanner.next();

                    // Convertir la date d'effet en LocalDate
                    LocalDate startDate = DateUtil.parseDate(startDateStr);

                    // Créer le prêt avec les informations saisies
                    Loan pret = new Loan(client, amount, rate, months, startDate);
                    pretService.addLoan(pret);

                    // Afficher les détails du prêt
                    System.out.println("Voici les détails du prêt :");
                    System.out.println("id : " + pret.getId());
                    System.out.println("client : " + client.getFirstname() + " " + client.getLastname());
                    System.out.println("montant emprunté : " + pret.getMontantDemande());
                    System.out.println("mensualité : " + pret.getMontantMensualite());

                    // Afficher les détails des mensualités
                    System.out.println("Date\t\tCapital remboursé\tPart des intérêts");
                    pret.getMensualites().forEach(mensualite -> {
                        System.out.println(mensualite.getDatePrelevement() + "\t" +
                                mensualite.getPartCapitalRembourse() + "\t\t" +
                                mensualite.getPartInteretsRembourses());
                    });
                    break;
                case 5:
                    System.out.println("Au revoir");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }
}
