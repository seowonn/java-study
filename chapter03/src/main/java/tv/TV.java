package tv;

public class TV {
	private int channel;
	private int volume;
	private boolean power;

	public TV(int channel, int volume, boolean power) {
		this.channel = channel;
		this.volume = volume;
		this.power = power;
	}

	public int getChannel() {
		return channel;
	}

	public int getVolume() {
		return volume;
	}

	public boolean isPower() {
		return power;
	}

	public void power(boolean flag) {
		this.power = flag;
	}
	
	public void channel(int channel) {
		this.channel = 1 <= channel && channel <= 255 ? channel : (channel < 1 ? 255 : 1);
	}
	
	public void channel(boolean up) {
		this.channel(this.channel + (up ? 1 : -1));
	}

	public void volume(int volume) {	
		this.volume = 0 <= volume && volume <= 100 ? volume : (volume > 100 ? 0 : 100);
	}
	
	public void volume(boolean up) {
		this.volume(this.volume + (up ? 1 : -1));
	}
	
	public void status() {
		System.out.printf("TV[channel=%d, volume=%d, power=%s] %n", this.channel, 
				this.volume, (this.power ? "on" : "off"));
	}
	
}
