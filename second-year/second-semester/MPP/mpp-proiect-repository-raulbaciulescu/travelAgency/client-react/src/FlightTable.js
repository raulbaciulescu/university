import React from "react";


class FlightRow extends React.Component{

    handleDelete = (event) => {
        console.log('delete button pentru ' + this.props.flight.id);
        this.props.deleteFunc(this.props.flight.id);
    }

    handleUpdate = (event) => {
        console.log('delete button pentru ' + this.props.flight.id);
        this.props.deleteFunc(this.props.flight.id);
    }

    render() {
        return (
            <tr>
                <td>{this.props.flight.id}</td>
                <td>{this.props.flight.start}</td>
                <td>{this.props.flight.destination}</td>
                <td>{this.props.flight.startDate}</td>
                <td>{this.props.flight.nrOfSeats}</td>
                <td><button  onClick={this.handleDelete}>Delete</button></td>
            </tr>
        );
    }
}

class FlightTable extends React.Component {
    render() {
        let rows = [];
        let deleteFunction = this.props.deleteFunc;
        this.props.flights.forEach(function(flight) {
            rows.push(<FlightRow flight={flight}  key={flight.id} deleteFunc={deleteFunction} />);
        });
        return (<div className="UserTable">

                <table className="center">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Start</th>
                        <th>Destination</th>
                        <th>Start Date</th>
                        <th>Nr Of Seats</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                </table>

            </div>
        );
    }
}

export default FlightTable;