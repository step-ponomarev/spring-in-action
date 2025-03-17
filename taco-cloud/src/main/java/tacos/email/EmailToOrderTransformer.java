package tacos.email;

import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import tacos.order.data.Taco;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<EmailOrder> {
    @Override
    protected AbstractIntegrationMessageBuilder<EmailOrder> doTransform(Message mailMessage) {
        final EmailOrder order = processPayload(mailMessage);
        return MessageBuilder.withPayload(order);
    }

    private EmailOrder processPayload(Message mailMessage) {
        try {
            // ... вытаскиваем все необходимое из письма
            return new EmailOrder(
                    ((InternetAddress) (mailMessage.getFrom()[0])).getAddress()
            );
        } catch (Throwable e) {
            return null;
        }
    }

    private EmailOrder parseEmailToOrder(String email, String content) {
        final EmailOrder order = new EmailOrder(email);

        // parse String content
        // ....
        //

        order.addTaco(new Taco());

        return order;
    }
}
