import React from "react";
import {addFlight, deleteFlight, getFlights, updateFlight} from "./utils/rest-calls";
import FlightForm from "./FlightForm";
import FlightTable from "./FlightTable";
import './FlightApp.css'

class FlightApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            flights: [],
            flightUpdate: null,
            deleteFunc: this.deleteFunc.bind(this),
            updateFunc: this.updateFunc.bind(this),
            addFunc: this.addFunc.bind(this)
        };
        console.log("Flight App constructor");
    }

    componentDidMount(){
        console.log('inside componentDidMount')
        getFlights().then(flights => {
            this.setState({flights})
            console.log(flights)
        });
    }

    addFunc(flight) {
        console.log("inside add func " + flight);
        addFlight(flight)
            .then(result => getFlights())
            .then(flights => this.setState({flights}))
            .catch(error => console.log('error add ', error));
    }

    updateFunc(flight){
        console.log('inside updateFunc ' + flight.id);
        updateFlight(flight)
            .then(result => getFlights())
            .then(flights => this.setState({flights}))
            .catch(error => console.log('error update', error));
    }

    deleteFunc(id){
        console.log('inside deleteFunc ' + id);
        deleteFlight(id)
            .then(result => getFlights())
            .then(flights => this.setState({flights}))
            .catch(error => console.log('error delete', error));
    }

    render() {
        return(
            <div className="FlightApp">
                <h1>Flights</h1>
                <FlightForm addFunc={this.state.addFunc} updateFunc={this.state.updateFunc}/>
                <br/>
                <br/>
                <FlightTable
                    flights={this.state.flights}
                    deleteFunc={this.state.deleteFunc}
                />
            </div>
        )
    }
}
export default FlightApp;