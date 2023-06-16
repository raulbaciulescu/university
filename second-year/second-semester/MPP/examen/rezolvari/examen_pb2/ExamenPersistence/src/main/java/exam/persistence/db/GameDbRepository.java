package exam.persistence.db;

import exam.model.Game;
import exam.persistence.IGameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDbRepository implements IGameRepository {
    private SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger();

    public GameDbRepository(SessionFactory sessionFactory) {
        logger.info("Initializing GameRepo with properties: {} ");
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Game add(Game elem) {
        logger.traceEntry("saving game {} ", elem);
        try(Session session=sessionFactory.openSession()){
            Transaction tran=null;
            try{
                tran=session.beginTransaction();
                session.save(elem);
                tran.commit();
                logger.trace("Saved {} conf", elem);
                logger.traceExit();
                return elem;
            }catch(RuntimeException ex){
                logger.error(ex);
                if(tran!=null)
                    tran.rollback();
            }
        }

        logger.traceExit();
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Game elem) {
        logger.traceEntry("updating user {}", elem);
        try(Session session=sessionFactory.openSession()){
            Transaction tran=null;
            try{
                tran=session.beginTransaction();
                Game foundGame =session.load(Game.class,elem.getID());
                foundGame.setPoints(elem.getPoints());
                foundGame.setFinished(elem.getFinished());
                foundGame.setValue(elem.getValue());
                session.update(foundGame);
                tran.commit();
                logger.trace("Updated user with id {}", elem.getID());

            }catch(RuntimeException ex){
                logger.error(ex);
                if(tran!=null)
                    tran.rollback();
            }
        }

        logger.traceExit();

    }

    @Override
    public Game findById(Integer integer) {
        logger.traceEntry();
        Game game=null;
        try(Session session=sessionFactory.openSession()){
            Transaction tran=null;
            try{
                tran= session.beginTransaction();
                game=session.get(Game.class,integer);
                tran.commit();
            }catch(RuntimeException ex){
                logger.error(ex);
                if(tran!=null)
                    tran.rollback();
            }
        }

        logger.traceExit();
        return game;
    }

    @Override
    public List<Game> findAll() {
        return null;
    }

    @Override
    public List<Game> findFinishedForPlayer(String alias) {
        logger.traceEntry();
        List<Game> games=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction tran=null;
            try{
                tran=session.beginTransaction();
                games=session.createQuery("from Game where alias= ? and finished=1",Game.class).setParameter(0,alias).list();
                tran.commit();
            }catch(RuntimeException ex){
                logger.error(ex);
                if(tran!=null)
                    tran.rollback();
            }
        }

        logger.traceExit();
        return games;
    }

    @Override
    public List<Game> getFinished() {
        logger.traceEntry();
        List<Game> games=new ArrayList<>();
        try(Session session=sessionFactory.openSession()){
            Transaction tran=null;
            try{
                tran=session.beginTransaction();
                games=session.createQuery("from Game where finished=1",Game.class).list();
                tran.commit();
            }catch(RuntimeException ex){
                logger.error(ex);
                if(tran!=null)
                    tran.rollback();
            }
        }

        logger.traceExit();
        return games;
    }
}
