import React from  'react';

class FlightForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            start: "",
            destination: "",
            startDate: "",
            nrOfSeats: ""
        };
    }

    handleIdChange = (event) => {
        this.setState({id: event.target.value});
    }

    handleStartChange = (event) => {
        this.setState({start: event.target.value});
    }

    handleDestinationChange = (event) => {
        this.setState({destination: event.target.value});
    }

    handleStartDateChange = (event) => {
        this.setState({startDate: event.target.value});
    }

    handleNrOfSeatsChange = (event) => {
        this.setState({nrOfSeats: event.target.value});
    }

    handleSubmit = (event) => {
        let flight = {
            id: this.state.id,
            start: this.state.start,
            destination: this.state.destination,
            startDate: this.state.startDate,
            nrOfSeats: this.state.nrOfSeats
        }
        console.log('A flight was submitted: ');
        console.log(flight);
        this.props.addFunc(flight);
        event.preventDefault();
    }

    handleUpdate = (event) => {
        let flight = {
            id: this.state.id,
            start: this.state.start,
            destination: this.state.destination,
            startDate: this.state.startDate,
            nrOfSeats: this.state.nrOfSeats
        }
        console.log('A flight was updated: ');
        console.log(flight);
        this.props.updateFunc(flight);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Id:
                    <input type="number" value={this.state.id} onChange={this.handleIdChange} />
                </label><br/><br/>
                <label>
                    Start:
                    <input type="text" value={this.state.start} onChange={this.handleStartChange} />
                </label><br/><br/>
                <label>
                    Destination:
                    <input type="text" value={this.state.destination} onChange={this.handleDestinationChange} />
                </label><br/><br/>
                <label>
                    StartDate:
                    <input type="date" value={this.state.startDate} onChange={this.handleStartDateChange} />
                </label><br/><br/>
                <label>
                    Number Of Seats:
                    <input type="number" value={this.state.nrOfSeats} onChange={this.handleNrOfSeatsChange} />
                </label><br/><br/>

                <button onClick={this.handleUpdate}>Update flight</button>
                <button onClick={this.handleSubmit}>Add flight</button>
            </form>

        );
    }
}

export default FlightForm;