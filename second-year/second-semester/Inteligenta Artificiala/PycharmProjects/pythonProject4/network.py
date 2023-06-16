import matplotlib.pyplot as plt


class Network:
    def __init__(self):
        self.layers = []

    def add_layer(self, layer):
        self.layers.append(layer)

    def predict(self, input):
        output = input
        for layer in self.layers:
            output = layer.forward(output)
        return output

    def train(self, loss, loss_prime, x_train, y_train, epochs = 10000, learning_rate = 0.1):
        error_list = []
        epoch_list = []
        for e in range(epochs):
            error = 0
            for x, y in zip(x_train, y_train):
                output = self.predict(x)

                error += loss(y, output)

                # backward
                grad = loss_prime(y, output)
                for layer in reversed(self.layers):
                    grad = layer.backward(grad, learning_rate)

            error /= len(x_train)
            error_list.append(error)
            epoch_list.append(e)

            print(f"{e + 1}/{epochs}, error={error}")

        plt.plot(error_list, epoch_list)
        plt.show()