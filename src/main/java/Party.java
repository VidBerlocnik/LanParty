public class Party {
    Integer Id;
    String Name;
    String Date;

    public Party(Integer id, String name, String date){
        Id = id;
        Name = name;
        Date = date;
    }
    public Party(){
        Id = null;
        Name = null;
        Date = null;
    }

    @Override
    public String toString() {
        return String.format(Name + "  |  " + Date);
    }
}
