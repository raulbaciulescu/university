package com.example.socnet.service;

import com.example.socnet.domain.model.Friendship;
import com.example.socnet.domain.model.Message;
import com.example.socnet.resources.Resources;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PDFService {

    public static void generateFriendsAndMessagesReport(final long offset,
                                                       final long interval)
            throws SQLException {
        final FriendshipService friendshipService =
                Resources.getInstance()
                        .getFriendshipSuperService()
                        .friendShipService();
        final MessageService messageService =
                Resources.getInstance().getChatSuperService()
                        .messageService();
        final List<Message> messages =
                messageService.getAll(offset, interval);
        final List<Friendship> friendships =
                friendshipService.getAll(offset, interval);
        final List<String> lines = new ArrayList<>();
        lines.add("messages:");
        for (final Message message : messages) {
            lines.add(message.toString());
        }
        lines.add("friendships:");
        for (final Friendship friendship : friendships) {
            lines.add(friendship.toString());
        }
        try {
            createPDFFile(lines, "friendships_and_messages.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateMessagesReport(final long userId,
                                             final long offset,
                                             final long interval)
            throws SQLException {
        final MessageService messageService =
                Resources.getInstance().getChatSuperService()
                        .messageService();

        final List<String> messages = messageService
                .getAll(offset, interval)
                .stream()
                .filter(message -> message.getSenderID() == userId)
                .map(Message::toString)
                .toList();
        try {
            createPDFFile(messages, "messages.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createPDFFile(final @NotNull List<String> lines,
                               final @NotNull String fileName) throws IOException {
        final PDDocument document = new PDDocument();
        final PDPage page = new PDPage();
        final PDPageContentStream contentStream =
                new PDPageContentStream(document, page);

        document.addPage(page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        final AtomicInteger lineOffset = new AtomicInteger(10);
        final List<String> reversedLines = new ArrayList<>(lines);
        Collections.reverse(reversedLines);
        for (String line : reversedLines) {
            contentStream.newLineAtOffset(0, lineOffset.getAndAdd(1));
            line = line.replace("\n", "").replace("\r", "");
            contentStream.showText(line);
        }
        contentStream.endText();
        contentStream.close();

        document.save(fileName);
        document.close();
    }
}
