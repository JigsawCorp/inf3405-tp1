public class CommunicationHandler {
    private boolean fAcceptClientCommunication;
    public CommunicationHandler()
    {
        fAcceptClientCommunication = false;
    }

    public void enableIncomingCommunication()
    {
        if (!fAcceptClientCommunication) {
            fAcceptClientCommunication = true;
            while (fAcceptClientCommunication) {

            }
        }
    }

    public void disableIncomingCommnication()
    {
        fAcceptClientCommunication = false;
    }


}
