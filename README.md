# incoming-order-adapter

This microservice, using Polling consumer, retrieves all files with matching file extensions (.csv or .xml) from the file system.

Information about all files which are available in the file system (including for those without the appropriate extension) is logged to the command line.

When the file with the relevant extension has been retrieved, Content Based Router placed it in the appropriate RAM line (direct:IncomingXml or direct:IncomingCsv).

From the memory queue file is retrieved and using the Message Translator converted to JSON format.

The message is then queued to the RabbitMQ message broker Queue.OrderSystem.NewOrderBatch.
