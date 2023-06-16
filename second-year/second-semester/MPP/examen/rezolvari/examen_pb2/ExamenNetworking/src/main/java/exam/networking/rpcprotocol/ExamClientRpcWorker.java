package exam.networking.rpcprotocol;

import exam.model.Player;
import exam.services.IExamObserver;
import exam.services.IExamServices;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
//este pe parte de server care deserveste workeri pt fiecare client pe o conexiune socket
public class ExamClientRpcWorker implements Runnable, IExamObserver {

    private IExamServices server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    //l-am pus volatile ca sa nu-l ia din memoria cache si sa fie fix ultima lui valoare
    private volatile boolean connected;

    // nu se modifica
    public ExamClientRpcWorker(IExamServices server, Socket connection){
        this.server=server;
        this.connection=connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    // nu se modifica
    @Override
    public void run() {
        while(connected){
            try{
                Object request=input.readObject();
                Response response=handleRequest((Request) request);
                if(response!=null)
                    sendResponse(response);

            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try{
            input.close();
            output.close();
            connection.close();
        } catch (IOException exception) {
            System.out.println("Error "+exception);
        }
    }

    // nu se modifica
    private void sendResponse(Response response) throws IOException {
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }


    /*
    @Override
    public void participantEnrolled(List<Integer> contestsList) throws SwimmingException {
        Response response=new Response.Builder().setType(ResponseType.ENROLLED_PARTICIPANT).setData(contestsList).build();
        System.out.println("Enrolled Participant at contests: "+contestsList);
        try{
            sendResponse(response);
        } catch (IOException exception) {
            throw new SwimmingException("Sending error: "+exception);
        }
    }
    */

    private static Response okResponse=new Response.Builder().setType(ResponseType.OK).build();
    private Response handleRequest(Request request){
        Response response=null;
        /*if(request.getType()==RequestType.LOGIN){
            System.out.println("Login request ..."+request.getType());
            Player player=(Player) request.getData();
            try{
                Player foundPlayer=server.login(player.getID(), this);
                return new Response.Builder().setType(ResponseType.LOGIN).setData(foundPlayer).build();
            }catch(ModelException e){
                connected=false;
                return new Response.Builder().setType(ResponseType.ERROR).setData(e.getMessage()).build();
            }
        }
        if(request.getType()==RequestType.MAKE_MOVE){
            System.out.println("MakeMoveRequest ...");
            MoveDTO moveDTO=(MoveDTO)  request.getData();
            try{
                Game game=server.makeMove(moveDTO.getGame(), moveDTO.getStep(), moveDTO.getFinished());
                //poate aici trb returnata lista de idContests?
                return new Response.Builder().setType(ResponseType.MAKE_MOVE).setData(game).build();

            }catch(ModelException e){
                return new Response.Builder().setType(ResponseType.ERROR).setData(e.getMessage()).build();
            }
        }
        if(request.getType()==RequestType.START){
            System.out.println("StartRequest ...");
            Game game=(Game)  request.getData();
            try{
                Game gameStart=server.startGame(game.getAlias(),game.getDate());

                return new Response.Builder().setType(ResponseType.START).setData(gameStart).build();

            }catch(ModelException e){
                return new Response.Builder().setType(ResponseType.ERROR).setData(e.getMessage()).build();
            }
        }
        if(request.getType()==RequestType.GET_LEADER_B0ARD){
            System.out.println("GetLeaderBoardRequest ...");
            List<GameDTO> games= null;
            try {
                games = (List<GameDTO>) server.getLeaderBoard();
            } catch (ModelException e) {
                e.printStackTrace();
            }
            return new Response.Builder().setType(ResponseType.GET_LEADER_BOARD).setData(games).build();
        }
        if(request.getType()==RequestType.LOGOUT){
            System.out.println("Logout request");
            String alias=(String)request.getData();
            try{
                server.logout(alias,this);
                connected=false;
                return okResponse;
            }catch(ModelException e){
                return new Response.Builder().setType(ResponseType.ERROR).setData(e.getMessage()).build();
            }
        }
        if(request.getType()==RequestType.GET_CONFIG){
            System.out.println("GetConfigRequest ...");
            Integer id=(Integer) request.getData();
            try{
                Configuration conf=server.getConfiguration(id);
                return new Response.Builder().setType(ResponseType.GET_CONFIG).setData(conf).build();

            }catch(ModelException e){
                return new Response.Builder().setType(ResponseType.ERROR).setData(e.getMessage()).build();
            }
        }
        */
        return response;
    }
}
