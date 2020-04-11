import React from "react";
import MaterialTable from "material-table";
import styled from 'styled-components';
import TablePagination from "@material-ui/core/TablePagination";


class Table extends React.Component {

    constructor() {
        super();
        this.state={
            selectedRow: null
        }
    }


    render(){
        const {title, columnsConfig=[], data=[]} = this.props;
        const divStyle = {
            backgroundColor: 'blue'
        };
        return(
            <div>
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
            <MaterialTable
                onRowClick={((evt, selectedRow) => this.setState({ selectedRow }))}
                style={divStyle}
                components={{
                    // Row: props => (<div>{console.log(props)}<span>{props.data.name}</span><span>{props.data.surname}</span></div>),
                    Pagination: props => (
                        <TablePagination
                            {...props}
                            labelRowsPerPage={<div style={{fontSize: 22}}>{props.labelRowsPerPage}</div>}
                            labelDisplayedRows={row => <div
                                style={{fontSize: 30}}>{props.labelDisplayedRows(row)}</div>}
                            SelectProps={{
                                style: {
                                    fontSize: 22
                                }
                            }}/>)
                }}

                columns={columnsConfig}
                data={data}

                options={{
                    toolbar: false,
                    search: false,
                    paging: true,
                    filtering: false,
                    headerStyle: {
                        backgroundColor: 'black',
                        color: 'red',
                        marginTop: '-5px'
                    },
                    rowStyle: rowData => ({
                        margin: '0 !important',
                        padding: '0 !important',
                        backgroundColor: (this.state.selectedRow && this.state.selectedRow.tableData.id === rowData.tableData.id) ? 'blue' : 'brown'})
                }}
            />
            </div>


        )



    }


}


export default Table;