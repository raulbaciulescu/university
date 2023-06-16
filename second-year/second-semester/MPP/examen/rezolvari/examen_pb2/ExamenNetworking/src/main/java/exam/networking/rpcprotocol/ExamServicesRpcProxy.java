package exam.networking.rpcprotocol;

import exam.services.ExamException;
import exam.services.IExamObserver;
import exam.services.IExamServices;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//suntem pe client pe service
public class ExamServicesRpcProxy implements IExamServices {
    private String host;
    private int port;
    private IExamObserver client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    // o punem volatile ca sa ia cea mai recenta valoare a variabilei
    private volatile boolean finished;

    public ExamServicesRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();
    }
/*
    @Override
    public OfficeWorker loginOfficeWorker(String username, String password, ISwimmingObserver client) throws SwimmingException {
        initializeConnection();
        OfficeWorker officeWorker = new OfficeWorker(null, username, password);
        Request request = new Request.Builder().setType(RequestType.LOGIN).setData(officeWorker).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.LOGIN) {
            this.client = client;
            return (OfficeWorker) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            closeConnection();
            throw new SwimmingException(error);
        }
        return null;
    }

    @Override
    public void logout(int idLoggedOfficeWorker, ISwimmingObserver client) throws SwimmingException {
        Request request = new Request.Builder().setType(RequestType.LOGOUT).setData(idLoggedOfficeWorker).build();
        sendRequest(request);
        Response response = readResponse();
        closeConnection();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new SwimmingException(error);
        }
    }

    @Override
    public Collection<ContestDTO> getContestsDTO() throws SwimmingException {
        Request request = new Request.Builder().setType(RequestType.GET_CONTESTS_DTO).build();
        sendRequest(request);
        Response response = readResponse();
//        if(response.getType()==ResponseType.ERROR){
//            String error=response.getData().toString();
//            throw new SwimmingException(error);
//        }
        List<ContestDTO> contestDTOS = (List<ContestDTO>) response.getData();
        return contestDTOS;
    }

    @Override
    public Collection<ParticipantDTO> getParticipantsDTO(int idContest) throws SwimmingException {
        Request request = new Request.Builder().setType(RequestType.GET_PARTICIPANTS_DTO).setData(idContest).build();
        sendRequest(request);
        Response response = readResponse();
        List<ParticipantDTO> participantDTOS = (List<ParticipantDTO>) response.getData();
        return participantDTOS;
    }

    @Override
    public Collection<Contest> getAllContests() throws SwimmingException {
        Request request = new Request.Builder().setType(RequestType.GET_ALL_CONTESTS).build();
        sendRequest(request);
        Response response = readResponse();
        List<Contest> contests = (List<Contest>) response.getData();
        return contests;
    }

    @Override
    public void enrollParticipant(String name, int age, List<Integer> contestsIdList, ISwimmingObserver client) throws SwimmingException {
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO(name, age, contestsIdList);
        Request request = new Request.Builder().setType(RequestType.ENROLL_PARTICIPANT).setData(enrollmentDTO).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new SwimmingException(error);
        }
    }
*/
    // nu se modifica
    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //nu se modifica
    private void sendRequest(Request request) throws ExamException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException exception) {
            throw new ExamException("Error sending object " + exception);
        }
    }

    // nu se modifica
    private Response readResponse() throws ExamException {
        Response response = null;
        try {
            response = qresponses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    // nu se modifica
    private void initializeConnection() throws ExamException {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    //nu se modifica
    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }

    private void handleUpdate(Response response) {
        /*if (response.getType() == ResponseType.ENROLLED_PARTICIPANT) {
            List<Integer> contestsId = (List<Integer>) response.getData();
            try {
                client.participantEnrolled(contestsId);
            } catch (SwimmingException e) {
                e.printStackTrace();
            }
        }
        */
    }

    private boolean isUpdate(Response response) {
        //return response.getType() == ResponseType.ENROLLED_PARTICIPANT;
        return false;
    }

    /*
    @Override
    public Player login(String alias, IModelObserver client) throws ModelException {
        initializeConnection();
        Player player = new Player(alias);
        Request request = new Request.Builder().setType(RequestType.LOGIN).setData(player).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.LOGIN) {
            this.client = client;
            return (Player) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            closeConnection();
            throw new ModelException(error);
        }
        return null;
    }

    @Override
    public void logout(String alias, IModelObserver client) throws ModelException {
        Request request = new Request.Builder().setType(RequestType.LOGOUT).setData(alias).build();
        sendRequest(request);
        Response response = readResponse();
        closeConnection();
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();
            throw new ModelException(error);
        }
    }

    @Override
    public Game startGame(String alias, LocalDateTime date) throws ModelException {
        Game game=new Game(null,-1,alias,date);
        Request request=new Request.Builder().setType(RequestType.START).setData(game).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.START) {
            return (Game) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();

            throw new ModelException(error);
        }
        return null;
    }

    @Override
    public Game makeMove(Game game, int step, int finished) throws ModelException {
        MoveDTO move =new MoveDTO(game,step,finished);
        Request request=new Request.Builder().setType(RequestType.MAKE_MOVE).setData(move).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.MAKE_MOVE) {
            return (Game) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();

            throw new ModelException(error);
        }
        return null;
    }

    @Override
    public Configuration getConfiguration(Integer id_conf) throws ModelException {
        Request request = new Request.Builder().setType(RequestType.GET_CONFIG).setData(id_conf).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.getType() == ResponseType.GET_CONFIG) {
            return (Configuration) response.getData();
        }
        if (response.getType() == ResponseType.ERROR) {
            String error = response.getData().toString();

            throw new ModelException(error);
        }
        return null;
    }

    @Override
    public List<GameDTO> getLeaderBoard() throws ModelException {
        Request request = new Request.Builder().setType(RequestType.GET_LEADER_B0ARD).build();
        sendRequest(request);
        Response response = readResponse();
        List<GameDTO> games = (List<GameDTO>) response.getData();
        return games;
    }

     */

    //nu se modifica
    private class ReaderThread implements Runnable {
        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("response received " + response);
                    if (isUpdate((Response) response)) {
                        handleUpdate((Response) response);
                    } else {

                        try {
                            qresponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }

}
