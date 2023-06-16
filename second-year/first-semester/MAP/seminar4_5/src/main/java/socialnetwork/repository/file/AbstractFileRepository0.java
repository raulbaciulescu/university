package socialnetwork.repository.file;

import socialnetwork.domain.Entity;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.memory.InMemoryRepository0;

import java.io.*;

import java.util.Arrays;
import java.util.List;


///Aceasta clasa implementeaza sablonul de proiectare Template Method; puteti inlucui solutia propusa cu un Factori (vezi mai jos)
public abstract class AbstractFileRepository0<ID, E extends Entity<ID>> extends InMemoryRepository0<ID,E> {
    String fileName;
    public AbstractFileRepository0(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
       try {
           BufferedReader br = new BufferedReader(new FileReader(fileName));
           String line = br.readLine();
           while ((line = br.readLine()) != null) {
               List<String> attributes = Arrays.asList(line.split(";"));
               E entity = extractEntity(attributes);
               save(entity);
           }
       } catch (FileNotFoundException e) {
           e.getMessage();
       } catch (IOException e1) {
           e1.printStackTrace();
       }

    }

    /**
     *  extract entity  - template method design pattern
     *  creates an entity of type E having a specified list of @code attributes
     * @param attributes
     * @return an entity of type E
     */
    public abstract E extractEntity(List<String> attributes);


    protected abstract String createEntityAsString(E entity);

    @Override
    public E save(E entity){
        E e = super.save(entity);
        if (e == null)
            writeToFile(entity);
        return e;
    }

    protected void writeToFile(E entity){
       String line = createEntityAsString(entity);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(fileName))){
            br.write(line);
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

