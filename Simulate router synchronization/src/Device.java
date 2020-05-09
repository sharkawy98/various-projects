public class Device extends Thread {
    public int takenConnection; //known to router and all other devices

    Device(String name, String type) {
        this.setName(name + "(" + type + ")"); //the name of this thread(device name & type)
    }


    @Override
    public void run() {
        Router.connection.P(this); //arrive
        Router.occupy(this); //establish connection with router

        System.out.println("Connection " + takenConnection + ": " + this.getName() + this.doActivities());

        Router.leave(this); //release connection with router
        Router.connection.V(); //notify other devices waiting
    }


    public String connect() {
//        try { Thread.sleep((long) Math.random()*1000 + 1); } catch (Exception e) {}
        return " Occupied";
    }

    public String doActivities() {
        try { Thread.sleep((long) Math.random()*3000 + 1); } catch (Exception e) {}
        return " performs online activity";
    }

    public String disconnect() {
//        try { Thread.sleep((long) Math.random()*1000 + 1); } catch (Exception e) {}
        return " Logged out";
    }

}
