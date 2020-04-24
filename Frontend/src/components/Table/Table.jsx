import React from "react";
import MaterialTable from "material-table";
import styled from 'styled-components';
import TablePagination from "@material-ui/core/TablePagination";


class Table extends React.Component {

    constructor() {
        super();
        this.state={
            selectedRow: null,
            rowsPerPage: 10
        }
    }

    componentDidUpdate(prevProps, prevState){
        // console.log(prevState.rowsPerPage);
        // console.log(this.state.rowsPerPage);
    }

    render(){
        const {columnsConfig=[], data=[], noRecordsMessage="Brak danych", onChangeCountPerPage, ownOnChangePage, count, page, onOrderChange} = this.props;
        const divStyle = {
            backgroundColor: '#292F2F',
            width: '600px',
        };
        return(
            <div style = {divStyle}>
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
                <MaterialTable
                onRowClick={((evt, selectedRow) => this.setState({ selectedRow }))}
                style={divStyle}
                localization={{
                    body: {
                        emptyDataSourceMessage: noRecordsMessage
                    }
                }}
                onOrderChange={(orderedColumnId, orderDirection) => onOrderChange(orderedColumnId, orderDirection)}


                components={{
                    // Row: props => (<div>{console.log(props)}<span>{props.data.name}</span><span>{props.data.surname}</span></div>),
                    Pagination: props => (
                        <TablePagination
                            {...props}
                            labelRowsPerPage={<div style={{fontSize: 22}}>{props.labelRowsPerPage}</div>}
                            count={count}
                            // rowsPerPage={this.state.rowsPerPage}
                            // rowsPerPageOptions={[10,30,50]}
                            onChangeRowsPerPage={event => {
                                props.onChangeRowsPerPage(event);
                                onChangeCountPerPage(event.target.value)
                                // this.setState({rowsPerPage: event.target.value});
                            }}
                            page={page}
                            onChangePage={(event, page) => {
                            props.onChangePage(event, page);
                            ownOnChangePage(page)

                            }
                            }
                            labelDisplayedRows={row => <div
                                style={{fontSize: 30, backgroundColor: 'blue'}}>{props.labelDisplayedRows(row)}</div>}
                            SelectProps={{
                                style: {
                                    fontSize: 22,
                                    backgroundColor: 'red',
                                }
                            }}/>)
                }}

                columns={columnsConfig}
                data={data}

                options={{
                    selection: true,
                    toolbar: false,
                    search: false,
                    paging: true,
                    filtering: false,
                    pageSize: this.state.rowsPerPage,
                    pageSizeOptions: [10, 30, 50],
                    paginationType: "stepped",
                    headerStyle: {
                        backgroundColor: '#292F2F',
                        backfaceVisibility: 'hidden',
                        color: '#FFD859',
                        marginTop: '-5px',
                    },
                    rowStyle: rowData => ({
                        color: 'white',
                        margin: '0 !important',
                        padding: '0 !important',
                        backgroundColor: (this.state.selectedRow && this.state.selectedRow.tableData.id === rowData.tableData.id) ? '#FFD859' : '#292F2F'}),
                         
                }}

            />
            </div>


        )



    }


}


export default Table;