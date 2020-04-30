import React from "react";
import MaterialTable from "material-table";
import styled from 'styled-components';
import TablePagination from "@material-ui/core/TablePagination";
import mystyle from "../../styles/tables.css";

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
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
        };

        const rowStyle = {
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
            width: '60px',
            // "&:hover": {
            //     backgroundColor: '#FFD859'
            //   }
        };
        return(
            <div styleName = {mystyle.table}>
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
                <MaterialTable
                onRowClick={((evt, selectedRow) => this.setState({ selectedRow }))}
                style={mystyle.table}
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
                            labelRowsPerPage={<div styleNName="table">{props.labelRowsPerPage}</div>}
                            count={count}
                            onChangeRowsPerPage={event => {
                                props.onChangeRowsPerPage(event);
                                onChangeCountPerPage(event.target.value)
                            }}
                            page={page}
                            onChangePage={(event, page) => {
                            props.onChangePage(event, page);
                            ownOnChangePage(page)

                            }
                            }
                            labelDisplayedRows={row => <div
                                style={mystyle.labelDisplayedRows}>{props.labelDisplayedRows(row)}</div>}
                                SelectProps={{
                                style: divStyle,
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
                    headerStyle: divStyle,
                    rowStyle: rowData => ({
                        ...rowStyle,
                        backgroundColor: (this.state.selectedRow && this.state.selectedRow.tableData.id === rowData.tableData.id) ? 'red!important' : 'blue!important'}),

                }}
                        //),


            />
            </div>


        )



    }


}


export default Table;