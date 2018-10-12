package Persistence2;

import java.util.ArrayList;

public class InventoryComponent {
	
	ArrayList<String> inventorySlots;


	public InventoryComponent(ArrayList<String> inventorySlots) {
		this.inventorySlots = inventorySlots;
	}
	
	public ArrayList<String> getInventory() {
		return inventorySlots;
	}

}
