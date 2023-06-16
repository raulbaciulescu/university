import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
//        PlayerRepository playerRepository = new PlayerRepositoryImpl();
//        GameRepository gameRepository = new GameRepositoryImpl();
//        // playerRepository.add(new Player("maria", "pass123"));
//        // System.out.println(playerRepository.findPlayer("maria", "pass123").get());
//
//        System.out.println(gameRepository.findLastGame());
////        gameRepository.add(new Game("ggg"));


//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 10; i++) {
//            stringBuilder.append('_');
//        }
//        System.out.println("da");
//        System.out.println("_".repeat(11));

//        ProposalRepository proposalRepository = new ProposalRepositoryImpl();
//        Proposal p = new Proposal(1, 'a', 2);
//        proposalRepository.add(p);

        Map<Integer, String> dd = new ConcurrentHashMap<>();
        dd.put(1, "da");
        dd.put(2, "ddda");
        dd.put(3, "fffa");

    }
}
