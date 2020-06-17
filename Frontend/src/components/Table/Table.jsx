import React from "react";
import MaterialTable from "material-table";
import TablePagination from "@material-ui/core/TablePagination";
import mystyle from "../../styles/tables.css";
import button from "../../styles/buttons.css";
class Table extends React.Component {

    constructor() {
        super();
        this.state={
            rowsPerPage: 10,
            isDetails: false
        }
    }

    componentDidMount() {
    }


    componentDidUpdate(prevProps, prevState){
        if(this.props.columnsConfig !== prevProps.columnsConfig && this.props.onDetailsClick) {
            let columnsConfig = this.props.columnsConfig
            columnsConfig.push(
                {
                    title: 'Detale',
                    field: '',
                    render: rowData => <button className = "detaleButton" onClick={() => this.props.onDetailsClick(rowData)}>Detale</button>,
                    sorting: false
                })
            this.setState({columnsConfig: columnsConfig})
        }
    }

    render(){
        const {data=[], noRecordsMessage="Brak danych", onChangeCountPerPage, ownOnChangePage, count, page, onOrderChange} = this.props;
        let columnsConfig = this.state.columnsConfig;
        console.log(columnsConfig);

        const divStyle = {
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
            whiteSpace: 'nowrap',
        };

        const rowStyle = {
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
            width: '5%',
            // "&:hover": {
            //     backgroundColor: '#FFD859'
            //   }
        };
        return(
            <div styleName = {mystyle.table}>
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
                <MaterialTable
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
                }
                }


            />
            </div>


        )



    }


}


export default Table;