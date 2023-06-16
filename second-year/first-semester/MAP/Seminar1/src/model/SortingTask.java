package model;

public class SortingTask extends Task {

    private int[] vect;
    private int size;

    public SortingTask(String taskID, String description, int[] vect, int size){
        super(taskID, description);
        this.vect = new int[vect.length];
        this.vect = vect;
        this.size = size;
    }


    public void bubble_sort(){
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (vect[j] > vect[j + 1]) {
                    int temp = vect[j];
                    vect[j] = vect[j + 1];
                    vect[j + 1] = temp;
                }
    }
    @Override
    public void execute() {
        bubble_sort();
        int i;
        for (i = 0; i < size; i++)
            System.out.println(vect[i]);
    }
}
