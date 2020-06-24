import React, { Component } from 'react';
import './App.css';
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';
import axios from "axios";

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      columnDefs: [{
        headerName: "Symbol", field: "symbol"
      }, {
       headerName: "Price", field: "price"
      }, {
        headerName: "Trend", field: "trend"
        ,cellRenderer: (info) => {
          if(info.data.trend)
          {
              return 'Up';
          } else {
              return 'Down';
          }
        }
      }]
    }
  }

  componentDidMount() {
    var self = this;
    axios.get('http://localhost:8060/api/getStockPriceDetails')
    .then(function (response) {
      console.log(response);
      var state = self.state;
      state.rowData = response.data;
      self.setState(state);
    }).catch(function (error) {
      console.log(error);
    });

  }


  render() {
    console.log(this.state);
    return (
      <div
        className="ag-theme-alpine"
        style={{
        height: '600px',
        width: '500px' }}
      >
        <AgGridReact
          columnDefs={this.state.columnDefs}
          rowData={this.state.rowData}>
        </AgGridReact>
      </div>
    );
  }
}

export default App;