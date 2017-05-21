/**
 * Created by Clayza on 2017-05-14.
 */
public class Group<N,T> {

public N  name;
public T type;


public Group(N name, T type) {
        this.name = name;
        this.type = type;
    }
public void setPair(N name,T type){
    this.name= name;
    this.type=type;
}
public N getName(){
return name;}

public T getType(){
    return  type;
}

}
