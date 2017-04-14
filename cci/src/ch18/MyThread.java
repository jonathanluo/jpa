package ch18;

import java.util.ArrayList;

class MyThread extends Thread {
	long time;
	ArrayList<Resource> res = new ArrayList<Resource>(); 
	public ArrayList<Resource> getRes() {
		return res;
	}

	public void run() {
		/* Run infinitely */
		time = System.currentTimeMillis();
		int count = 0;
		while (true) {
			if (count < 4){
				if (Q18_4.canAcquireResource(this, Q18_4.r[count])) {
					res.add(Q18_4.r[count]);
					count++;
					System.out.println("Resource: [" + Q18_4.r[count - 1].getId() + "] acquired by thread: [" + this.getName() + "]");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				this.stop();
			}
		}
	}

	public long getTime() { 
		return time;
	}
	public void setRes(ArrayList<Resource> res) {
		this.res = res;
	}
	MyThread(String name) {
		super(name);
	}
}