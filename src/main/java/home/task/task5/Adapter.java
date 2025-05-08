package home.task.task5;

interface USBC {
    void connectDeviceWithUSBTypeC();
}

class FlashDrive {
    void connectDeviceWithUSBTypeA() {
        System.out.println("Successful connection");
    }
}

public class Adapter implements USBC {
    private FlashDrive flashDrive;

    public Adapter(FlashDrive flashDrive) {
        this.flashDrive = flashDrive;
    }

    @Override
    public void connectDeviceWithUSBTypeC() {
        this.flashDrive.connectDeviceWithUSBTypeA();
    }
}

class TestAdapter {
    public static void main(String[] args) {
        USBC connect = new Adapter(new FlashDrive());
        connect.connectDeviceWithUSBTypeC();
    }
}