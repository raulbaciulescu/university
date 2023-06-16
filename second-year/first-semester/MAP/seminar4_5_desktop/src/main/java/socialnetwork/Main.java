package socialnetwork;

import socialnetwork.config.ApplicationContext;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.repository.Repository;
import socialnetwork.repository.Repository0;
import socialnetwork.repository.file.UtilizatorFile;
import socialnetwork.repository.file.UtilizatorFile0;
import socialnetwork.service.Controller;
import socialnetwork.service.FriendshipService;
import socialnetwork.service.UtilizatorService0;
import socialnetwork.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //String fileName=ApplicationContext.getPROPERTIES().getProperty("data.socialnetwork.users");
        String fileName="data/users.csv";

        //Repository<Long,Utilizator> userFileRepository2 = new UtilizatorFile(fileName, new UtilizatorValidator());

//        Utilizator utilizator = new Utilizator("Raulaaaaa", "Baciulescua");
//        utilizator.setId(8L);
//        userFileRepository.save(utilizator);
//
//        userFileRepository.findAll().forEach(System.out::println);
//        System.out.println("ok");




//        int op = 0;
//        do {
//            //print();
//            System.out.println("DA  ");
//            try {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                //op = Integer.parseInt(reader.read());
//                System.out.println(reader.read());
//                reader.close();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }while(op != 0);
        Repository0<Long,Utilizator> userFileRepository = new UtilizatorFile0(fileName, new UtilizatorValidator());

        UtilizatorService0 utilizatorService0 = new UtilizatorService0(userFileRepository);
        FriendshipService0 friendshipService0 = new FriendshipService0(userFileRepository);
        Ui ui = new Ui(new Controller())
    }
}


