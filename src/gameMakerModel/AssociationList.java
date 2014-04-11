package gameMakerModel;

import java.util.ArrayList;
import java.util.List;

public class AssociationList {
	private List<Association> list;

    public List<Association> getList() {
		return list;
	}

	public void setList(List<Association> list) {
		this.list = list;
	}

	public AssociationList(){
        list = new ArrayList<Association>();
    }

    public void add(Association association){
        list.add(association);
    }
}


