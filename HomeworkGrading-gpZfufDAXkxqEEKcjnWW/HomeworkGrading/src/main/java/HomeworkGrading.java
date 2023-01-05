// You are allowed to add imports here

import java.util.List;

/**
 * This class represents two assistants who grade homeworks.
 * Grading one homework takes around one second. But since the
 * two assistants split their work, they can grade twice as fast.
 * That means grading six homeworks should take around 3 seconds.
 * You should implement the assistants as two separate threads.
 *
 * You MUST use the basic Thread class.
 * DO NOT use futures and thread pools.
 *
 * Avoid race conditions.
 * Catch exceptions (and ignore them).
 */
public class HomeworkGrading {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    MyThread myThread1;
    MyThread myThread2;


    Thread thread1;
    Thread thread2;

    public class MyThread implements Runnable{

        List<Homework> list;
        int computed = 0;

        boolean grading = true;


        int count = 0;
        public MyThread(List<Homework> homeworks){
            this.list = homeworks;
        }

        @Override
        public void run() {
            for (Homework i : this.list){
                if (grading){
                    count+= i.grade();
                    computed++;
                } else {
                    break;
                }

            }
        }

        public int getCount(){
            return this.count;
        }

        public int getComputed(){
            return this.computed;
        }
    }

    private boolean isAlreadyUsed;


    // This is a homework to grade.
    // A homework contains several errors and takes around one second to grade.
    // Do not change this class.
    public static class Homework {
        private int numErrors;

        public Homework(int numErrors) {
            this.numErrors = numErrors;
        }

        public int grade() {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            return 20-numErrors;
        }
    }

    /**
     * Starts grading a list of homeworks.
     * This method will use two threads (the two assistants) to grade the homeworks.
     * Both assistants should more or less grade half of the homeworks.
     * This method returns immediately. It does NOT wait for the
     * two assistants to finish grading.
     *
     * You can assume that this method will only be called once.
     * You can assume that the list is not empty.
     */
    public void startGrading(List<Homework> homeworks) {
        // this method can only be called once
        if(isAlreadyUsed) {
            return;
        }
        isAlreadyUsed=true;
        int length = homeworks.size();
        List<Homework> l1 = homeworks.subList(0, length/2);
        List<Homework> l2 = homeworks.subList(length/2, length);

        this.myThread1 = new MyThread(l1);
        this.myThread2 = new MyThread(l2);

        this.thread1 = new Thread(this.myThread1);
        this.thread2 = new Thread(this.myThread2);

        this.thread1.start();
        this.thread2.start();
        return;
        // TODO
    }

    /**
     * This method waits until the assistants have finished their work.
     */
    public void waitForGrading() {
        // TODO
        try{
            this.thread1.join();
            this.thread2.join();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method returns the current number of homeworks that have been
     * graded so far.
     * It is allowed to call this method while the assistants are still
     * working. In that case, the method returns the number of homeworks that
     * have been already finished so far.
     */
    public int getNumberOfFinishedGradedHomeworks() {
        // TODO
        int number = this.myThread1.getComputed() + this.myThread2.getComputed();
         return number;
    }

    /**
     * This method returns the current average grade of the homeworks
     * graded so far.
     * It is allowed to call this method while the assistants are still
     * working. In that case, the method returns the average of the homeworks that
     * have been already finished so far.
     */
    public int getAverageGrade() {
        // TODO
        int numberGraded = getNumberOfFinishedGradedHomeworks();
        if (numberGraded == 0){
            return 0;
        } else {
            int gradeSum = this.myThread1.getCount() + this.myThread2.getCount();
            return gradeSum/numberGraded;
        }

    }

    /**
     * This methods tells the assistants to stop grading.
     * The assistants will finish grading the homeworks they are currently
     * working on and then they will stop and not grade anymore the
     * remaining homeworks.
     */
    public void cancelGrading() {
        // TODO
        this.myThread1.grading = false;
        this.myThread2.grading = false;
    }
}
